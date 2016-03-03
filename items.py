#ITEM CLASS
class Item():
	"""The base class for all items"""
	def __init__(self, name, description, value):
		self.name = name
		self.description = description
		self.value = value
	def __str__(self):
		return "{}\n=====\n{}\nValue: {}\n".format(self.name, self.description, self.value)

class Gold(Item):
	def __init__(self, amount):
		self.amount = amount
		Item.__init__(self, "Gold", "The currency", self.amount)
