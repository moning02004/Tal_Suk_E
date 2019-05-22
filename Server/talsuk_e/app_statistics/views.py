from django.contrib.auth.models import User
from django.db.models import Sum
from django.views.decorators.csrf import csrf_exempt
from django.http import JsonResponse

from .models import Year
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


def temp(request):
    return JsonResponse({'message': 'OK'})
