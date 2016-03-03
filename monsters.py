class Monster:
	def __init__(	self, name, lvl, rtype, 
						hp, mp, atk, defd, agi	): 
		self.name = name
		self.lvl = lvl
		self.rtype = rtype
		self.hp = hp
		self.mp = mp
		self.atk = atk
		self.defend = defd
		self.agi = agi
	def attack(self, enemydef):
		return atk - (enemydef / (random.randint(1, 3)))
	def is_alive(self):
		return self.hp > 0

class Werewolf(Monster):
	def __init__(self):
		Monster.__init__(self, "Werewolf", 1,"beast", 10, 3, 5, 2, 3)

class Slime(Monster):
	def __init__(self):
		Monster.__init__(self, "Slime", 1, "slime", 6, 5, 2, 1, 2)

class BeastSlime(Monster):
	def __init__(self):
		Monster.__init__(self, "Beast Slime", 1, "slime", 12, 7, 4, 2, 6)

class Fusion:
	def __init__(self, rtype, fusionlist):
		self.type = rtype
		self.fusionlist = fusionlist
	def checklist(self, player, monster1, monster2):
		for monster in fusionlist.keys():
			if monster2 == monster:
				result = fusionlist[monster]
				answer = raw_input("The resulting monster will be %s, is that okay? " % result.name)
				if answer.lower() in ["1", "y", "ye", "yes", "okay", "ok"]:
					self.changemonster(self, player, mon1, mon2, result)
				else:
					print "Fusion was cancelled."
	def changemonster(self, player, mon1, mon2, result):
		player.removemonster(mon1)
		player.removemonster(mon2)
		player.addmonster(result)
			

class SlimeFusion(Fusion):
	def __init__(self):
		self.dict = { "Werewolf" : BeastSlime(), }
		Fusion.__init__(self, "slime", self.dict)
