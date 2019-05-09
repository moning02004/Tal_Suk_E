from django.contrib.auth.models import User
from django.http import HttpResponse, JsonResponse
from django.views.decorators.csrf import csrf_exempt
from .models import SukE

import json


@csrf_exempt
def login(request):
    data = json.loads(request.body.decode('utf-8'))
    username = data['username']
    if User.objects.all().filter(username=username).exists() and SukE.objects.all().filter(user=User.objects.get(username=username)).exists():
        suke = SukE.objects.get(user=User.objects.get(username=username))
        if suke.user.check_password(data['password']):
            return JsonResponse({'permission': 'user', 'message': 'Exist'})
    return JsonResponse({'message':'Not Found'})


@csrf_exempt
def register(request):
    data = json.loads(request.body.decode('utf-8'))
    username = data['username']
    password = data['password']
    try:
        user = User.objects.create_user(username=username, password=password)
        user.save()
        suke = SukE()
        suke.user = user
        suke.name = data['name']
        suke.address1 = data['address1']
        suke.address2 = data['address2']
        suke.save()
        return JsonResponse({'message': 'Success'})
    except:
        return JsonResponse({'message': 'Fail'})


@csrf_exempt
def check(request, pk):
    message = 'Success' if not User.objects.all().filter(username=pk).exists() else 'Fail'
    return JsonResponse({'message': message})


@csrf_exempt
def leave(request, pk):
    try:
        user = User.objects.get(username=pk)
        user.delete()
        message = 'Success'
    except:
        message = 'Failed'
    return JsonResponse({'message': message})
