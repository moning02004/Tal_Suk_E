from django.contrib.auth.models import User
from django.views.decorators.csrf import csrf_exempt
from django.http import JsonResponse

from .models import Info
import json


@csrf_exempt
def index(request):
    try:
        data = json.loads(request.body.decode('utf-8'))
        message = dict()
        message['info'] = []
        for info in reversed(Info.objects.all()):
            info_json = dict()
            info_json['title'] = info.title
            info_json['content'] = info.content
            info_json['created'] = (info.created).strftime("%Y.%m.%d %H:%M")
            message['info'].append(info_json)
        return JsonResponse(message, safe=False)
    except:
        return JsonResponse({'message': 'Fail'})


@csrf_exempt
def new(request):
    try:
        data = json.loads(request.body.decode('utf-8'))
        info = Info()
        info.title = data['title']
        info.content = data['content']
        info.save()
        return JsonResponse({'message': 'Success'})
    except:
<<<<<<< Updated upstream
        return JsonResponse({'message': 'Fail'})
=======
        return JsonResponse({'message': 'Fail'})
>>>>>>> Stashed changes
