from django.urls import path
from . import views


app_name='app_user'
urlpatterns = [
    path('__login_/', views.login),
    path('__register_/', views.register),
    path('__check_/<str:pk>/', views.check),
    path('__leave_/<str:pk>/', views.leave)
]
