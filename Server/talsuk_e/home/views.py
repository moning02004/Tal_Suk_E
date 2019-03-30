from django.http import HttpResponse, JsonResponse
from django.views.decorators.csrf import csrf_exempt


@csrf_exempt
def index(request):
    return JsonResponse({'message': 'OK'})


@csrf_exempt
def update(request):
    return JsonResponse({'message': 'OK'})