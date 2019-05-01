from django.contrib.auth.models import User
from django.db.models import Sum
from django.http import JsonResponse
from collections import OrderedDict

from .models import Year, Month, Day
import json


def me(request):
    stat = OrderedDict()
    stat['year'] = []
    years = Year.objects.all()
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
                print(json.dumps(stat_month, ensure_ascii=False, indent='\t'))
            stat_year['month'].append(stat_month)
        stat['year'].append(stat_year)

    print(json.dumps(stat, ensure_ascii=False, indent='\t'))
    return JsonResponse(json.dumps(stat, ensure_ascii=False), safe=False)


def local(request):
    return JsonResponse({})


def save(request):
    username = request.user
    print(username)
    year = 2019
    num = 5
    year = Year.objects.get(num=year) if Year.objects.all().filter(num=year).exists() else Year(num=year, user=User.objects.get(username=username))
    year.save()
    month = Month.objects.get(year=year, num=num) if Month.objects.all().filter(year=year, num=num).exists() else Month(year=year, num=num)
    month.save()
    day = Day()
    day.num = 1
    day.month = month
    day.fee = 50
    day.weight = 450
    day.save()
    return JsonResponse({})
