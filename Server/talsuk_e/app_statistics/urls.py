from django.urls import path
from . import views


app_name='app_statistics'
urlpatterns = [
    path('__me_/', views.me),
    path('temp/', views.temp),
]

