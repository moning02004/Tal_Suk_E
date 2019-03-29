from django.urls import path
from . import views


app_name='user'
urlpatterns = [
    path('__login_/', views.login, name='login'),
    path('__register_/', views.register, name='register'),
    path('__check_/<str:pk>/', views.check, name='check'),
]
