from django.contrib.auth.models import User
from django.db.models import Sum
from django.views.decorators.csrf import csrf_exempt
from django.http import JsonResponse
from collections import OrderedDict

from .models import Year, Month, Day
import json


@csrf_exempt
def me(request):
    data = json.loads(request.body.decode('utf-8'))
    username = data['username']
    print(username)
    stat = OrderedDict()
    stat['year'] = []
    years = Year.objects.all().filter(user=User.objects.get(username=username))
    for year in years:
        stat_year = OrderedDict()
        stat_year['num'] = year.num
        stat_year['month'] = []
        for month in year.month_set.all():
            stat_month = OrderedDict()
            stat_month['num'] = month.num
            stat_month['count'] = month.day_set.all().count()
            stat_month['weight'] = month.day_set.aggregate(sum = Sum('weight'))['sum']
            stat_month['fee'] = month.day_set.aggregate(sum = Sum('fee'))['sum']
            stat_month['day'] = []
            for day in month.day_set.all():
                stat_day = OrderedDict()
                stat_day['num'] = day.num
                stat_day['weight'] = day.weight
                stat_day['fee'] = day.fee
                stat_month['day'].append(stat_day)
            stat_year['month'].append(stat_month)
        stat['year'].append(stat_year)

    print(json.dumps(stat, ensure_ascii=False, indent='\t'))
    return JsonResponse(stat, safe=False)


@csrf_exempt
def local(request):
    return JsonResponse({})


@csrf_exempt
def save(request):
    data = json.loads(request.body.decode('utf-8'))
    username = data['username']
    print(data['username'])
    year = int(data['year'])
    month = int(data['month'])
    day = int(data['day'])

    new_year = Year.objects.get(num=year, user=User.objects.get(username=username)) if Year.objects.all().filter(user=User.objects.get(username=username), num=year).exists() else Year(num=year, user=User.objects.get(username=username))
    new_year.save()
    new_month = Month.objects.get(year=new_year, num=month) if Month.objects.all().filter(year=new_year, num=month).exists() else Month(year=new_year, num=month)
    new_month.save()
    
    new_day = Day()
    new_day.num = day
    new_day.month = new_month
    new_day.fee = 50
    new_day.weight = 450
    new_day.save()
    return JsonResponse({'message':'Success'})
