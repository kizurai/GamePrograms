import monsters
import random

#AREA CLASS
class Area:
	def __init__(self, name, choicenum, choices):
		self.name = name
		self.num = choicenum
		self.choices = choices
	def getname(self):
		return self.name
	def getnum(self):
		return self.num
	def checknum(self, number):
		return self.num >= number
	def showchoices(self):
		for choice in self.choices:
			print choice

class City(Area):
	def __init__(self):
		self.choices = [	"1. Rest at an Inn.", "2. Go to the Item Shop.",
								"3. Go to the Weapon Shop.", "4. Fuse monsters."]
		Area.__init__(self,"city", 4, self.choices)
	def chooseoption(self, player, option):
		if option == "1":
			self.restInn()
		elif option == "2":
			self.itemshop()
		elif option == "3":
			self.weaponshop()
		elif option == "4":
			self.monsterfuse(player)
	def restInn(self):
		print "You healed at the Inn."
	def itemshop(self):
		print "item shop"
	def weaponshop(self):
		print "weapon shop"
	def monsterfuse(self, player):
		print "Welcome to the Monster Fusion Shop!"
		if player.monsters < 2:
			print "I'm sorry, it doesn't seem like you have enough monsters to fuse. Please come back next time."
		else:
			player.seeteam()
			print "Which 2 monsters would you like to fuse?"
			mon1=raw_input("Monster 1: ")
			mon2=raw_input("Monster 2: ")
			if mon1 <= player.monsters or mon2 <= player.monsters:
				print "You will be fusing %s and $s." (player.team[mon1], player.team[mon2])
				#monsters.checklist

class Forest(Area):
	def __init__(self):
		self.choices = ["1. Explore"]
		Area.__init__(self, "forest", 1, self.choices)
	def chooseoption(self, player, option):
		if option == "1":
			self.explore()
	def explore(self):
		roll = random.randint(0, 100)
		if roll >= 90:
			self.find_item()
		elif roll > 50 and roll < 90:
			self.random_battle()
		else:
			print ("You found nothing.")
	def random_battle(self):
		print "Found a monster."
	def find_item(self):
		print "Found an item."

