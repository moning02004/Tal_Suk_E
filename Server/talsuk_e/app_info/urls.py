from django.urls import path
from . import views


app_name='app_info'
urlpatterns = [
    path('__index_/', views.index, name='index'),
    path('__detail_/', views.detail, name='detail'),
]
