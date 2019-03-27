from django.urls import path
from . import views


app_name='function'
urlpatterns = [
    path('', views.index, name='index'),
]