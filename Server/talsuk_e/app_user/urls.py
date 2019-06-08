from django.urls import path
from . import views


app_name='app_user'
urlpatterns = [
    path('__login_/', views.login),
    path('__register_/', views.register),
    path('__edit_/', views.edit),
    path('__check_/<str:pk>/', views.check),
    path('__leave_/', views.leave),
    path('__get_info_/', views.getInfo),
    path('__get_all_/', views.get_user_all),
    path('__reset_password_/', views.reset_password),
]

