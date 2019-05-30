from django.contrib.auth.models import User
from django.db import models


class SukE(models.Model):
    user = models.OneToOneField(User, on_delete=models.CASCADE)
    name = models.CharField(max_length=100)
    phone = models.CharField(max_length=20)
    fee = models.PositiveIntegerField(default=72)


    def __str__(self):
        return self.user.username

