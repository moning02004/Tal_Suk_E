from django.contrib.auth.models import User
from django.db import models


class SukE(models.Model):
    user = models.OneToOneField(User, on_delete=models.CASCADE)
    address1 = models.CharField(max_length=200)
    address2 = models.CharField(max_length=200)
    name = models.CharField(max_length=100)

    def __str__(self):
        return self.user
