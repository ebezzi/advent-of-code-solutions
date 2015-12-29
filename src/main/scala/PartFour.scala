

object Day22Part2 extends App {

  def normalize(x: Int) = if (x>1) x else 1

  trait Entity {
    var hp: Int
    def damage(v: Int) { hp = hp - normalize(v) }
    def isDead = hp <= 0
    override def toString = s"$hp hp"
  }

  class Fighter(var hp: Int, val dmg: Int) extends Entity

  class Wizard(var hp: Int, var mana: Int) extends Entity {

    var totalManaSpent = 0

    def heal(v: Int) { hp = hp+v }
    def recharge(m: Int) { mana = mana + m }
    private def spend(m: Int) = { 
      if (m > mana) throw new Exception("OOM!")
      mana = mana - m 
      totalManaSpent += m 
    }

    def cast(spell: Spell) = spell match {
      case Missile => spend(53)
      case Drain => spend(73)
      case Poison => spend(173)
      case Shield => spend(113)
      case Recharge => spend(229)
    }

    def oom = mana < 0

    override def toString = s"$hp hp, $mana mana"
  }

  class Effects {
    case class Effect(rem: Int)

    var poison: Option[Effect] = None
    var shield: Option[Effect] = None
    var recharge: Option[Effect] = None

    def setPoison { poison = Some(Effect(6)) }
    def setShield { shield = Some(Effect(6)) }
    def setRecharge { recharge = Some(Effect(5)) }

    def advance {
      def adv(e: Option[Effect]) = e match {
        case Some(Effect(i)) if i > 0 => Some(Effect(i-1))
        case otherwise => None
      }

      poison = adv(poison)
      shield = adv(shield)
      recharge = adv(recharge)
    }

  }

  trait Spell
  case object Missile extends Spell
  case object Drain extends Spell
  case object Poison extends Spell
  case object Shield extends Spell
  case object Recharge extends Spell

  def fight(rotation: Seq[Spell]) : Option[Int] = {

    val effects = new Effects

    val player = new Wizard(50, 500)
    val boss = new Fighter(58, 9)

    def playerTurn(spells: Seq[Spell]) : Entity = {

      player.damage(1)

      if (player.isDead) return boss
      if (boss.isDead) return player

      effects.poison foreach { m => boss.damage(3) }
      effects.recharge foreach { m => player.recharge(101) }

      spells.head match {
        case Missile => boss.damage(4)
        case Drain => boss.damage(2); player.heal(2)
        case Poison => effects.setPoison
        case Shield => effects.setShield
        case Recharge => effects.setRecharge
      }

      player.cast(spells.head)

      effects.advance

      bossTurn(spells.tail :+ spells.head)

    }

    def bossTurn(spells: Seq[Spell]) : Entity = {

      if (player.isDead) return boss
      if (boss.isDead) return player

      effects.poison foreach { m => boss.damage(3)  }
      effects.recharge foreach { m => player.recharge(101) }

      val dmg = effects.shield match {
        case Some(s) => boss.dmg - 7
        case None => boss.dmg
      }

      player.damage(dmg)

      effects.advance

      playerTurn(spells)

    }

    import scala.util._

    Try(playerTurn(rotation)) match {
      case Success(winner) if winner == player => Some(player.totalManaSpent)
      case otherwise => None
    }

  }


  val res = Seq(Shield, Poison, Recharge, Shield, Poison, Recharge, Shield, Poison, Recharge, Missile, Missile, Drain)
    .permutations
    .map { rotation => (rotation, fight(rotation)) }
    .filter { case (k,v) => v.isDefined }
    .minBy { case (k,v) => v }

  println (res)

}





object Day22 extends App {

  def normalize(x: Int) = if (x>1) x else 1

  trait Entity {
    var hp: Int
    def damage(v: Int) { hp = hp - normalize(v) }
    def isDead = hp <= 0
    override def toString = s"$hp hp"
  }

  class Fighter(var hp: Int, val dmg: Int) extends Entity

  class Wizard(var hp: Int, var mana: Int) extends Entity {

    var totalManaSpent = 0

    def heal(v: Int) { hp = hp+v }
    def recharge(m: Int) { mana = mana + m }
    private def spend(m: Int) = { 
      if (m > mana) throw new Exception("OOM!")
      mana = mana - m 
      totalManaSpent += m 
    }

    def cast(spell: Spell) = spell match {
      case Missile => spend(53)
      case Drain => spend(73)
      case Poison => spend(173)
      case Shield => spend(113)
      case Recharge => spend(229)
    }

    def oom = mana < 0

    override def toString = s"$hp hp, $mana mana"
  }

  class Effects {
    case class Effect(rem: Int)

    var poison: Option[Effect] = None
    var shield: Option[Effect] = None
    var recharge: Option[Effect] = None

    def setPoison { poison = Some(Effect(6)) }
    def setShield { shield = Some(Effect(6)) }
    def setRecharge { recharge = Some(Effect(5)) }

    def advance {
      def adv(e: Option[Effect]) = e match {
        case Some(Effect(i)) if i > 0 => Some(Effect(i-1))
        case otherwise => None
      }

      poison = adv(poison)
      shield = adv(shield)
      recharge = adv(recharge)
    }

  }

  trait Spell
  case object Missile extends Spell
  case object Drain extends Spell
  case object Poison extends Spell
  case object Shield extends Spell
  case object Recharge extends Spell

  def fight(rotation: Seq[Spell]) : Option[Int] = {

    val effects = new Effects

    val player = new Wizard(50, 500)
    val boss = new Fighter(58, 9)

    def playerTurn(spells: Seq[Spell]) : Entity = {

      if (player.isDead) return boss
      if (boss.isDead) return player

      effects.poison foreach { m => boss.damage(3) }
      effects.recharge foreach { m => player.recharge(101) }

      spells.head match {
        case Missile => boss.damage(4)
        case Drain => boss.damage(2); player.heal(2)
        case Poison => effects.setPoison
        case Shield => effects.setShield
        case Recharge => effects.setRecharge
      }

      player.cast(spells.head)

      effects.advance

      bossTurn(spells.tail :+ spells.head)

    }

    def bossTurn(spells: Seq[Spell]) : Entity = {

      if (player.isDead) return boss
      if (boss.isDead) return player

      effects.poison foreach { m => boss.damage(3)  }
      effects.recharge foreach { m => player.recharge(101) }

      val dmg = effects.shield match {
        case Some(s) => boss.dmg - 7
        case None => boss.dmg
      }

      player.damage(dmg)

      effects.advance

      playerTurn(spells)

    }

    import scala.util._

    Try(playerTurn(rotation)) match {
      case Success(winner) if winner == player => Some(player.totalManaSpent)
      case otherwise => None
    }

  }


  val res = Seq(Shield, Poison, Recharge, Shield, Poison, Recharge, Shield, Poison, Recharge, Missile, Missile, Drain)
    .permutations
    .map { rotation => (rotation, fight(rotation)) }
    .filter { case (k,v) => v.isDefined }
    .minBy { case (k,v) => v }

  println (res)

}

object Day22Debug {

  def normalize(x: Int) = if (x>1) x else 1

  trait Entity {
    var hp: Int
    def damage(v: Int) { hp = hp - normalize(v) }
    def isDead = hp <= 0
    override def toString = s"$hp hp"
  }

  class Fighter(var hp: Int, val dmg: Int) extends Entity

  class Wizard(var hp: Int, var mana: Int) extends Entity {

    var totalManaSpent = 0

    def heal(v: Int) { hp = hp+v }
    def recharge(m: Int) { mana = mana + m }
    private def spend(m: Int) = { 
      if (m > mana) throw new Exception("OOM!")
      mana = mana - m 
      totalManaSpent += m 
    }

    def cast(spell: Spell) = spell match {
      case Missile => spend(53)
      case Drain => spend(73)
      case Poison => spend(173)
      case Shield => spend(113)
      case Recharge => spend(229)
    }

    def oom = mana < 0

    override def toString = s"$hp hp, $mana mana"
  }

  class Effects {
    case class Effect(rem: Int)

    var poison: Option[Effect] = None
    var shield: Option[Effect] = None
    var recharge: Option[Effect] = None

    def setPoison { poison = Some(Effect(6)) }
    def setShield { shield = Some(Effect(6)) }
    def setRecharge { recharge = Some(Effect(5)) }

    def advance {
      def adv(e: Option[Effect]) = e match {
        case Some(Effect(i)) if i > 0 => Some(Effect(i-1))
        case otherwise => None
      }

      poison = adv(poison)
      shield = adv(shield)
      recharge = adv(recharge)
    }

  }

  trait Spell
  case object Missile extends Spell
  case object Drain extends Spell
  case object Poison extends Spell
  case object Shield extends Spell
  case object Recharge extends Spell

  val effects = new Effects

  // Demo stats
  // val player = new Wizard(10, 250)
  // val boss = new Fighter(14, 8)
  // val rotation = Seq(Recharge, Shield, Drain, Poison, Missile)

  // record: 1511

  val player = new Wizard(50, 500)
  val boss = new Fighter(58, 9)
  // val rotation = Seq(Shield, Recharge, Poison, Missile)
  // val rotation = Seq(Shield, Recharge, Poison, Missile, Shield, Recharge, Poison)
  val rotation = Seq(Shield, Missile, Poison, Missile, Recharge, Drain)

  def playerTurn(spells: Seq[Spell]) : Entity = {

    if (player.isDead) return boss
    if (boss.isDead) return player

    println(s"-- Player turn --")
    println(s"- Player has $player")
    println(s"- Boss has $boss")

    effects.poison foreach { m => boss.damage(3) ; println ("Poison does 3 damage...") }
    effects.recharge foreach { m => player.recharge(101) }

    spells.head match {
      case Missile => boss.damage(4)
      case Drain => boss.damage(2); player.heal(2)
      case Poison => effects.setPoison
      case Shield => effects.setShield
      case Recharge => effects.setRecharge
    }

    // if (player.oom) throw new Exception("Mana negative!")

    player.cast(spells.head)

    println (s"Player casts ${spells.head}.")

    effects.advance

    println

    bossTurn(spells.tail :+ spells.head)

  }

  def bossTurn(spells: Seq[Spell]) : Entity = {

    if (player.isDead) return boss
    if (boss.isDead) return player

    println(s"-- Boss turn --")
    println(s"- Player has $player")
    println(s"- Boss has $boss")

    effects.poison foreach { m => boss.damage(3) ; println ("Poison does 3 damage...") }
    effects.recharge foreach { m => player.recharge(101) }

    val dmg = effects.shield match {
      case Some(s) => boss.dmg - 7
      case None => boss.dmg
    }

    player.damage(dmg)

    println(s"Boss attacks for $dmg!")

    effects.advance

    println

    playerTurn(spells)

  }

  val winner = playerTurn(rotation)

  if (winner == boss) println ("*** BOSS WON ***") else println ("!!! PLAYER WON !!!")

  println (s"Total mana spent: ${player.totalManaSpent}")

}




// object Day22 {

//   def normalize(x: Int) = if (x>1) x else 1


//   trait Entity
//   case class Fighter(name: String, hp: Int, dmg: Int, debuff: Option[Status]) extends Entity
//   case class Wizard(name: String, mana: Int, armor: Int, buffs: Seq[Status]) extends Entity
//   // val boss = Fighter("boss", 103, 9, 2)

//   trait Status
//   trait Spell
//   case object Missile extends Spell
//   case object Drain extends Spell
//   case class Poison(remaining: Int) extends Spell with Status
//   case class Shield(remaining: Int) extends Spell with Status
//   case class Recharge(remaining: Int) extends Spell with Status


//   def fight(player: Wizard, rotation: Seq[Spell]) = {

//     def calculateEffects(player: Wizard, boss: Fighter, effects: Seq[Status]) = {

//     }

//     def nextRound(player: Wizard, boss: Fighter, spells: Seq[Spell], effects: Seq[Status]): Entity = {

//       // order is -> calculate debuffs, calculate next boss status, calculate buffs, calculate next player status





//       // debuffs
//       // val debuffedBoss = boss.debuff match { 
//       //   case Some(Poison(remaning)) if remaning == 0 => 
//       // }

//       // calculate boss next status
//       // val nextBoss = spells.head match {
//       //   case Missile => Fighter("boss", boss.hp - 4, boss.dmg, None)
//       //   case Drain => Fighter("boss", boss.hp - 2, boss.dmg, None)
//       //   case Poison(remaining) if remaining == 0 => Fighter("boss", boss.hp - 3, boss.dmg, None)
//       //   case Poison(remaining) => Fighter("boss", boss.hp - 3, boss.dmg, Poison(remaining-1))
//       //   case otherwise => boss
//       // }

//       // calculate player next status

//       // val buffedPlayer = spells.head match {
//       //   case 
//       // }

//       if (next2.hp < 1) return first
//       if (next1.hp < 1) return second

//       nextRound(next1, next2)
//     }

//     nextRound(player, boss)

//   }

//   for ( dmg <- 4 to 13; armor <- 0 to 10) {
//     val player = Fighter("player", 100, dmg, armor)
//     val winner = fight(player)
//     if ("boss" == winner.name) println (player)
//   }



// }
