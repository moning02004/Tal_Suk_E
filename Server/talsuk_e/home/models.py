from django.contrib.auth.models import User
from django.db import models


class Item(models.Model):
    user = models.OneToOneField(User, on_delete=models.CASCADE)
    weight = models.PositiveIntegerField(default=0)
    fee = models.PositiveIntegerField(default=0)
    saved = models.DateTimeField(auto_now=True)

    def __str__(self):
        return self.user


class Average(models.Model):
    user = models.OneToOneField(User, on_delete=models.CASCADE)
    average = models.CharField(max_length=100)

    def __str__(self):
        return self.average


class Statistics(models.Model):
    average = models.ForeignKey(Average, on_delete=models.CASCADE)
    amount = models.PositiveIntegerField(default=0)
    peroid_from = models.DateTimeField()
    peroid_to = models.DateTimeField()

    def __str__(self):
        return self.amount
