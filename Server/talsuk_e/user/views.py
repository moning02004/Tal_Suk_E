from django.contrib.auth.models import User
from django.http import HttpResponse, JsonResponse
from django.views.decorators.csrf import csrf_exempt
from .models import SukE

import json


@csrf_exempt
def login(request):
    data = json.loads(request.body)
    username = data['username']
    if User.objects.all().filter(username=username).exists() and SukE.objects.all().filter(user=User.objects.get(username)).exists():
        user = SukE.objects.get(user=User.objects.get(username))
        if user.check_password(data['password']):
            if user.is_superuser:
                return JsonResponse({'permission': 'admin', 'message': 'Success'})
            else:
                return JsonResponse({'permission': 'user', 'message': 'Success'})
    return JsonResponse({'message': 'Fail'})


@csrf_exempt
def register(request):
    data = json.loads(request.body)
    username = data['username']
    password = data['password']
    name = data['name']
    address1 = data['address1']
    address2 = data['address2']

    try:
        user = User.objects.create_user(username=username, password=password)
        user.save()
        suke = SukE()
        suke.user = user
        suke.name = name
        suke.address1 = address1
        suke.address2 = address2
        suke.save()
        return JsonResponse({'message': 'Success'})
    except:
        return JsonResponse({'message': 'Fail'})
