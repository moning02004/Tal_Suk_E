from django.contrib.auth.models import User
from django.db import models


class Year(models.Model):
    user = models.ForeignKey(User, on_delete=models.CASCADE)
    num = models.PositiveIntegerField(default=2019)

    def __str__(self):
        return str(self.num)


class Month(models.Model):
    year = models.ForeignKey(Year, on_delete=models.CASCADE)
    num = models.PositiveIntegerField(default=1)

    def __str__(self):
        return str(self.num)


class Day(models.Model):
    month = models.ForeignKey(Month, on_delete=models.CASCADE)
    num = models.PositiveIntegerField(default=1)
    weight = models.PositiveIntegerField(default=0)
    fee = models.CharField(max_length=10, default='0')

    def __str__(self):
        return str(self.num)
