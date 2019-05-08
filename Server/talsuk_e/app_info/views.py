from django.contrib.auth.models import User
from django.views.decorators.csrf import csrf_exempt
from django.http import JsonResponse
from collections import OrderedDict

from app_user.models import SukE
from .models import Info
import json


@csrf_exempt
def index(request):
    data = json.loads(request.body.decode('utf-8'))
    print(data)

    message = OrderedDict()
    message['info'] = []
    for info in Info.objects.all():
        info_json = OrderedDict()
        info_json['iid'] = info.id
        info_json['title'] = info.title
        info_json['content'] = info.content
        info_json['created'] = str( info.created)
        message['info'].append(info_json)
    print(json.dumps(message, ensure_ascii=False, indent='\t'))
    return JsonResponse(message, safe=False)


@csrf_exempt
def new(request):
    data = json.loads(request.body.decode('utf-8'))
    # info = Info()
    # info.title = data['title']
    # info.content = data['content']
    # info.save()
    print(data)
    return JsonResponse({'message':'Success'})
