from django.contrib.auth.models import User
from django.http import HttpResponse, JsonResponse
from django.views.decorators.csrf import csrf_exempt
from .models import SukE

import json


@csrf_exempt
def login(request):
    data = json.loads(request.body.decode('utf-8'))
    username = data['username']
    if SukE.objects.all().filter(user=User.objects.get(username=username)).exists():
        suke = SukE.objects.get(user=User.objects.get(username=username))
        if suke.user.check_password(data['password']):
            return JsonResponse({'permission': 'admin' if suke.user.is_superuser else 'user' , 'message': 'Success'})
    return JsonResponse({'message': 'Fail'})


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
def edit(request):
    data = json.loads(request.body.encode('utf-8'))
    try:
        user = User.objects.get(username=data['username'])
        user.suke.name = data['name']
        user.suke.phone = data['phone']
        user.set_password(data['password']) if not data['password'] == '' else None
        user.save()
        return JsonResponse({'message': 'Success'})
    except:
        return JsonResponse({'message': 'Fail'})


@csrf_exempt
def check(request, pk):
    return JsonResponse({
        'message': 'Success' \
        if not User.objects.all().filter(username=pk).exists() \
        else 'Fail'
    })


@csrf_exempt
def leave(request):
    try:
        data = json.loads(request.body.encode('utf-8'))
        username = data['username']
        user = User.objects.get(username=username)
        user.delete()
        message = 'Success'
    except:
        message = 'Fail'
    return JsonResponse({'message': message})


<<<<<<< Updated upstream

=======
>>>>>>> Stashed changes
