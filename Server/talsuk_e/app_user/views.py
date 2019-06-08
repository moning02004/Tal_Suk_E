from django.contrib.auth.models import User
from django.http import HttpResponse, JsonResponse
from django.views.decorators.csrf import csrf_exempt
from .models import SukE

import json


@csrf_exempt
def login(request):
    data = json.loads(request.body.decode('utf-8'))
    username = data['username']
    try:
        if SukE.objects.all().filter(user=User.objects.get(username=username)).exists():
            suke = SukE.objects.get(user=User.objects.get(username=username))
            if suke.user.check_password(data['password']):
                return JsonResponse({'permission': 'admin' if suke.user.is_superuser else 'user', 'message': 'Success','fee':suke.fee})
    except:
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
        suke.phone = data['phone']
        suke.save()
        return JsonResponse({'message': 'Success'})
    except:
        return JsonResponse({'message': 'Fail'})


@csrf_exempt
def edit(request):
    data = json.loads(request.body.decode('utf-8'))
    user = User.objects.get(username=data['username'])
    print(data)
    try:
        if user.check_password(data['current_password']):
            user.suke.name = data['name']
            user.suke.phone = data['phone']
            user.suke.fee = data['fee']
            user.suke.save()
            user.set_password(data['password']) if not data['password'] == '' else None
            user.save()
            return JsonResponse({'message': 'Success'})
        else:
            return JsonResponse({'message': 'Fail'})
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
        data = json.loads(request.body.decode('utf-8'))
        user = User.objects.get(username=data['username'])
        if user.check_password(data['password']):
            user.delete()
            message = 'Success'
        else:
            message = 'Fail'
    except:
        message = 'Fail'
    return JsonResponse({'message': message})


@csrf_exempt
def getInfo(request):
<<<<<<< Updated upstream
    try:
        data = json.loads(request.body.decode('utf-8'))
        user = User.objects.get(username=data['username'])
        user_info = dict()
        user_info['name'] = user.suke.name
        user_info['phone'] = user.suke.phone
        user_info['fee'] = user.suke.fee
        return JsonResponse(user_info, safe=False)
=======
    data = json.loads(request.body.decode('utf-8'))
    user = User.objects.get(username=data['username'])
    user_info = dict()
    user_info['name'] = user.suke.name
    user_info['phone'] = user.suke.phone
    user_info['fee'] = user.suke.fee

    return JsonResponse(user_info, safe=False)


@csrf_exempt
def get_user_all(request):
    user_info = dict()
    user_info['user_info'] = []

    for x in SukE.objects.all():
        user_x = dict()
        user_x['username'] = x.user.username
        user_x['name'] = x.name
        user_x['phone'] = x.phone
        user_info['user_info'].append(user_x)
    return JsonResponse(user_info, safe=False)
 

@csrf_exempt
def reset_password(request):
    try:
        data = json.loads(request.body.decode('utf-8'))
        user = User.objects.get(username=data['username'])
        user.set_password('123456789')
        user.save()
        return JsonResponse({'message': 'Success'})
>>>>>>> Stashed changes
    except:
        return JsonResponse({'message': 'Fail'})
