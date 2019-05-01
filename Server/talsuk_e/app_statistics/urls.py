from django.urls import path
from . import views


app_name='app_statistics'
urlpatterns = [
    path('__me_/', views.me, name='me'),
    path('__local_/', views.local, name='local'),
    path('__save_/', views.save, name='save'),
]
