from django.contrib.auth.models import User
from django.db.models import Sum
from django.views.decorators.csrf import csrf_exempt
from django.http import JsonResponse
from collections import OrderedDict

from app_user.models import SukE
from .models import Year, Month, Day
import json


@csrf_exempt
def me(request):
    data = json.loads(request.body.decode('utf-8'))
    username = data['username']
    print(username)
    me_stat = OrderedDict()
    me_stat['year'] = []
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
        me_stat['year'].append(stat_year)

    print(json.dumps(me_stat, ensure_ascii=False, indent='\t'))

    return JsonResponse(me_stat, safe=False)


@csrf_exempt
def local(request):
    data = json.loads((request.body).decode('utf-8'))
    username = data['username']
    print(username)
    address1 = User.objects.get(username=username).suke.address1
    address2 = User.objects.get(username=username).suke.address2

    sum = dict()
    local_stat = OrderedDict()
    stat_year = OrderedDict()
    stat_month = OrderedDict()

    suke_list = SukE.objects.all().filter(address1=address1, address2=address2)
    local_stat = OrderedDict()
    stat_year = OrderedDict()
    stat_month = OrderedDict()

    for suke in suke_list:
        local_stat['year'] = []
        for year in Year.objects.all().filter(user=suke.user):
            stat_year['num'] = year.num
            stat_year['month'] = []
            for month in Month.objects.all().filter(year=year):
                sum[month.num] = [0, 0] if not month.num in sum.keys() else [(sum[month.num])[0], (sum[month.num])[1]]
                (sum[month.num])[0] += month.day_set.aggregate(sum=Sum('weight'))['sum']
                (sum[month.num])[1] += month.day_set.aggregate(sum=Sum('fee'))['sum']

    for key, value in sum.items():
        stat_month = OrderedDict()
        stat_month['num'] = key
        stat_month['weight'] = value[0]
        stat_month['fee'] = value[1]
        stat_year['month'].append(stat_month)
    print(json.dumps(stat_year, ensure_ascii=False, indent='\t'))
    return JsonResponse(stat_year, safe=False)

@csrf_exempt
def save(request):
    data = json.loads(request.body.decode('utf-8'))
    username = data['username']
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

