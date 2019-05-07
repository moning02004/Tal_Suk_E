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
    return JsonResponse({})


@csrf_exempt
def detail(request):
    data = json.loads(request.body.decode('utf-8'))
    print(data)
    return JsonResponse({})