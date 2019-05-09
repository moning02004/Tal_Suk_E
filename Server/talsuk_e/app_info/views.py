from django.contrib.auth.models import User
from django.views.decorators.csrf import csrf_exempt
from django.http import JsonResponse

from app_user.models import SukE
from .models import Info
import json


@csrf_exempt
def index(request):
    print("AAAAA")
    data = json.loads(request.body.decode('utf-8'))
    print(data)
    message = dict()
    message['info'] = []
    for info in reversed(Info.objects.all()):
        info_json = dict()
        info_json['title'] = info.title
        info_json['content'] = info.content
        info_json['created'] = (info.created).strftime("%Y.%m.%d %H:%M")
        message['info'].append(info_json)
    return JsonResponse(message, safe=False)


@csrf_exempt
def new(request):
    data = json.loads(request.body.decode('utf-8'))
    info = Info()
    info.title = data['title']
    info.content = data['content']
    info.save()
    return JsonResponse({'message':'Success'})
