

object Day22 {

  def normalize(x: Int) = if (x>1) x else 1


  trait Entity
  case class Fighter(name: String, hp: Int, dmg: Int, debuff: Option[Status]) extends Entity
  case class Wizard(name: String, mana: Int, armor: Int, buffs: Seq[Status]) extends Entity
  // val boss = Fighter("boss", 103, 9, 2)

  trait Status
  trait Spell
  case object Missile extends Spell
  case object Drain extends Spell
  case class Poison(remaining: Int) extends Spell with Status
  case class Shield(remaining: Int) extends Spell with Status
  case class Recharge(remaining: Int) extends Spell with Status


  def fight(player: Wizard, rotation: Seq[Spell]) = {

    def nextRound(player: Wizard, boss: Fighter, spells: Seq[Spell]): Entity = {

      // order is -> calculate debuffs, calculate next boss status, calculate buffs, calculate next player status

      // debuffs
      val debuffedBoss = boss.debuff match { 
        case Some(Poison(remaning)) if remaning == 0 => 
      }

      // calculate boss next status
      // val nextBoss = spells.head match {
      //   case Missile => Fighter("boss", boss.hp - 4, boss.dmg, None)
      //   case Drain => Fighter("boss", boss.hp - 2, boss.dmg, None)
      //   case Poison(remaining) if remaining == 0 => Fighter("boss", boss.hp - 3, boss.dmg, None)
      //   case Poison(remaining) => Fighter("boss", boss.hp - 3, boss.dmg, Poison(remaining-1))
      //   case otherwise => boss
      // }

      // calculate player next status

      // val buffedPlayer = spells.head match {
      //   case 
      // }

      if (next2.hp < 1) return first
      if (next1.hp < 1) return second

      nextRound(next1, next2)
    }

    nextRound(player, boss)

  }

  for ( dmg <- 4 to 13; armor <- 0 to 10) {
    val player = Fighter("player", 100, dmg, armor)
    val winner = fight(player)
    if ("boss" == winner.name) println (player)
  }



}
