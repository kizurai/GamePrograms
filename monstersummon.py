#!/usr/bin/env python
import time
import monsters, items, areas

AREALIST = ["City", "Forest"]

#CHARACTER CLASS
class Player:
	def __init__(self):
		self.name = ""
		self.monsters = 1
		self.maxmonsters = 3
		self.position = areas.City()
		self.inventory = [items.Gold(100)]
		self.team = [monsters.Werewolf()]
		self.end = False
	def setpos(self, pos):
		self.position = pos
	def getpos(self):
		return self.position
	def checkmaxmonster(self):
		return ((monsters + 1) > maxmonsters)
	def addmonster(self, newmonster):
		self.team.append(newmonster)
		self.monsters = self.monsters + 1
	def removemonster(self, monstername):
		for monster in self.team:
			if monstername == monster.name:
				self.team.remove(monster)
	def seeinventory(self):
		print "-----------------"
		for item in self.inventory:
			print "|%s\t|%d\t|" % (item.name, item.value)
		print "-----------------"
	def seeteam(self):
		count = 1
		print "--------------------------------"
		for monster in self.team:
			print "%d|%s\t|%d\t|%d\t|" % (count, monster.name, monster.lvl, monster.hp)
			count = count + 1
		print "--------------------------------"
	def move(self):
		for place in AREALIST:
			print place
		whereto = raw_input("Where would you like to go? ")
		if whereto.lower() in ["1", "city", "c", "1. city"]:
			if self.position.getname() == "city":
				print "You're already at the %s." % self.position.getname()
			else:
				self.setpos(areas.City())
		elif whereto.lower() in ["2", "forest", "f", "2. forest"]:
			if self.position.getname() == "forest":
				print "You're already at the %s." % self.position.getname()
			else:
				self.setpos(areas.Forest())
		else:
				print "%s does not exist." % where
	def quit(self):
		print "exiting game"
		self.end = True

CMDS = {
	'quit': Player.quit,	
	'exit': Player.move,
	'inventory': Player.seeinventory,
	'team': Player.seeteam,
	'move' : Player.move,
	'go' : Player.move,
	}

#Main Menu
def menu(player):
	commandFound = False
	place = player.getpos()
	print ("You are currently in the %s.") % place.getname()
	place.showchoices()
	command = raw_input("What would you like to do? [1-%d]: " % place.getnum())
	print ""
	for c in CMDS.keys():
		if command == c[:len(command)]:
			CMDS[c](player)
			commandFound = True
	if not commandFound:
		if place.checknum(int(command)):
			place.chooseoption(player, command)
		else:
			print "%s is not an option." % command
	#time.sleep(1)

#main function
def game():
	print ("******************")
	print ("* Monster Summon *")
	print ("******************")

	p = Player()
	p.pos = "city"
	p.name = raw_input("Input name: ")
	print "Hello %s" % p.name
	
	while not p.end:
		menu(p)

if __name__ == "__main__":
	game()
