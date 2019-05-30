from django.contrib.auth.models import User
from django.db.models import Sum
from django.views.decorators.csrf import csrf_exempt
from django.http import JsonResponse

from .models import Year, Month, Day
from datetime import datetime
import json


@csrf_exempt
def me(request):
    data = json.loads(request.body.decode('utf-8'))
    username = data['username']
    total_stat = dict()
    total_stat['year'] = []

    year_list = Year.objects.all().filter(user=User.objects.get(username=username))
    for year in year_list:
        stat_year = dict()
        stat_year['num'] = year.num
        stat_year['month'] = []
        for month in reversed(year.month_set.all()):
            stat_month = dict()
            stat_month['num'] = month.num
            stat_month['weight'] = month.day_set.aggregate(sum=Sum('weight'))['sum']
            stat_month['fee'] = month.day_set.aggregate(sum=Sum('fee'))['sum']
            stat_month['day'] = []
            for day in reversed(month.day_set.all()):
                stat_day = dict()
                stat_day['num'] = day.num
                stat_day['weight'] = day.weight
                stat_day['fee'] = day.fee
                stat_month['day'].append(stat_day)
            stat_year['month'].append(stat_month)
        total_stat['year'].append(stat_year)
    print(json.dumps(total_stat, ensure_ascii=False, indent='\t'))
    return JsonResponse(total_stat, safe=False)


@csrf_exempt
def add(request):
    data = json.loads(request.body.decode('utf-8'))
    today = datetime.now()

    user = User.objects.get(username=User.objects.get(username=data['username']))
    year = Year(user=user, num=today.year) if not today.year in [x.num for x in user.year_set.all()] else user.year_set.get(num=today.year)
    year.save()
    
    month = Month(year=year, num=today.month) if not today.month in [x.num for x in year.month_set.all()] else year.month_set.get(num=today.month)
    month.save()
    
    day = Day(month=month, num=today.day, weight=int(data['weight']), fee=int(data['fee'])) if not today.day in [x.num for x in month.day_set.all()] else month.day_set.get(num=today.day)
    day.save()

    return JsonResponse({'message': 'OK'})

