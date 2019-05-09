from django.contrib.auth.models import User
from django.db.models import Sum
from django.views.decorators.csrf import csrf_exempt
from django.http import JsonResponse

from app_user.models import SukE
from .models import Year, Month, Day
import json


@csrf_exempt
def me(request):
    data = json.loads(request.body.decode('utf-8'))
    username = data['username']
    print(username)
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
def local(request):
    data = json.loads((request.body).decode('utf-8'))
    username = data['username']

    suke_list = SukE.objects.all().filter(address1=User.objects.get(username=username).suke.address1, address2=User.objects.get(username=username).suke.address2)
    total_stat = dict()
    sum = dict()

    for suke in suke_list:
        for year in reversed(suke.user.year_set.all()):
            total_stat['num'] = year.num
            total_stat['month'] = []
            for month in reversed(year.month_set.all()):
                sum[month.num] = [0, 0] if not month.num in sum.keys() else [(sum[month.num])[0], (sum[month.num])[1]]
                (sum[month.num])[0] += month.day_set.aggregate(sum=Sum('weight'))['sum']
                (sum[month.num])[1] += month.day_set.aggregate(sum=Sum('fee'))['sum']

    for key, value in sum.items():
        stat_month = dict()
        stat_month['num'] = key
        stat_month['weight'] = value[0]
        stat_month['fee'] = value[1]
        total_stat['month'].append(stat_month)
    print(json.dumps(total_stat, ensure_ascii=False, indent='\t'))
    return JsonResponse(total_stat, safe=False)

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
    new_day.weight = 450
    new_day.fee = new_day.weight*1/1000*72
    new_day.save()
    return JsonResponse({'message':'Success'})

