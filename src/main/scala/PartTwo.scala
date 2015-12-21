
object Day21 {

  // Hit Points: 103
  // Damage: 9
  // Armor: 2

  def trunc(x: Int) = if(x>1) x else 1

  case class Fighter(name: String, hp: Int, dmg: Int, armor: Int)
  val boss = Fighter("boss", 103, 9, 2)
  // val player = Fighter("player", 100, 9, 1)

  // 9,3 o 8,4

  def fight(player: Fighter) = {

    def nextRound(first: Fighter, second: Fighter): Fighter = {
      val next1 = Fighter(first.name, first.hp - trunc(second.dmg - first.armor), first.dmg, first.armor)
      val next2 = Fighter(second.name, second.hp - trunc(first.dmg - second.armor), second.dmg, second.armor)

      // println (next1, next2)

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

  // val winner = fight(player)

  // println (   s"The winner is: $winner"   )



}

object Day8Take2  {

  import org.apache.commons.lang3._

  // val input = Seq("qxfcsmh", "ffsfyxbyuhqkpwatkjgudo", "byc\x9dyxuafof\\\xa6uf\\axfozomj\\olh\x6a", "jtqvz", "uzezxa\"jgbmojtwyfbfguz", "vqsremfk\x8fxiknektafj", "wzntebpxnnt\"vqndz\"i\x47vvjqo\"", "higvez\"k\"riewqk", "dlkrbhbrlfrp\\damiauyucwhty", "d\"", "qlz", "ku", "yy\"\"uoao\"uripabop", "saduyrntuswlnlkuppdro\\sicxosted", "tj", "zzphopswlwdhebwkxeurvizdv", "xfoheirjoakrpofles\"nfu", "q\xb7oh\"p\xce\"n", "qeendp\"ercwgywdjeylxcv", "dcmem", "\"i\x13r\"l", "ikso\xdcbvqnbrjduh\"uqudzki\xderwk", "wfdsn", "pwynglklryhtsqbno", "hcoj\x63iccz\"v\"ttr", "zf\x23\\hlj\\kkce\\d\\asy\"yyfestwcdxyfj", "xs", "m\"tvltapxdvtrxiy", "bmud", "k\"a", "b\"oas", "\"yexnjjupoqsxyqnquy\"uzfdvetqrc", "vdw\xe3olxfgujaj", "qomcxdnd\"\\cfoe\"", "fpul", "m\"avamefphkpv", "vvdnb\\x\\uhnxfw\"dpubfkxfmeuhnxisd", "hey\\", "ldaeigghlfey", "eure\"hoy\xa5iezjp\\tm", "yygb\"twbj\\r\"\x10gmxuhmp\"", "weirebp\x39mqonbtmfmd", "ltuz\\hs\"e", "ysvmpc", "g\x8amjtt\"megl\"omsaihifwa", "yimmm", "iiyqfalh", "cwknlaaf", "q\x37feg\xc6s\"xx", "uayrgeurgyp\\oi", "xhug\"pt\"axugllbdiggzhvy", "kdaarqmsjfx\xc3d", "\"vkwla", "d\"", "tmroz\"bvfinxoe\\mum\"wmm", "\"n\"bbswxne\\p\\yr\"qhwpdd", "skzlkietklkqovjhvj\xfe", "pbg\\pab\"bubqaf\"obzcwxwywbs\\dhtq", "xxjidvqh\"lx\\wu\"ij", "daef\x5fe\x5b\\kbeeb\x13qnydtboof", "ogvazaqy\"j\x73", "y", "n\"tibetedldy\\gsamm\"nwu", "wldkvgdtqulwkad", "dpmxnj", "twybw\"cdvf\"mjdajurokbce", "ru\"\\lasij\"i", "roc\\vra\\lhrm", "pbkt\x60booz\"fjlkc", "j\x4dytvjwrzt", "\\uiwjkniumxcs", "cbhm\"nexccior\"v\"j\"nazxilmfp\x47", "qdxngevzrlgoq", "\"lrzxftytpobsdfyrtdqpjbpuwmm\x9e", "mdag\x0asnck\xc2ggj\"slb\"fjy", "wyqkhjuazdtcgkcxvjkpnjdae", "aixfk\xc0iom\x21vueob", "dkiiakyjpkffqlluhaetires", "ysspv\"lysgkvnmwbbsy", "gy\"ryexcjjxdm\"xswssgtr", "s", "ddxv", "qwt\"\x27puilb\"pslmbrsxhrz", "qdg\xc9e\\qwtknlvkol\x54oqvmchn\\", "lvo", "b", "fk\"aa\"\"yenwch\\\\on", "srig\x63hpwaavs\\\x80qzk\"xa\"\xe6u\\wr", "yxjxuj\"ghyhhxfj\"\xa6qvatre", "yoktqxjxkzrklkoeroil", "\"jfmik\"", "smgseztzdwldikbqrh\"", "jftahgctf\"hoqy", "tcnhicr\"znpgckt\"ble", "vqktnkodh\"lo\"a\\bkmdjqqnsqr", "ztnirfzqq", "s", "xx", "iqj\"y\\hqgzflwrdsusasekyrxbp\\ad", "\\xzjhlaiynkioz\"\"bxepzimvgwt", "s\x36rbw", "mniieztwrisvdx", "atyfxioy\x2b\\", "irde\x85\x5cvbah\\jekw\"ia", "bdmftlhkwrprmpat\"prfaocvp", "w\\k", "umbpausy", "zfauhpsangy", "p\"zqyw", "wtztypyqvnnxzvlvipnq\"zu", "deicgwq\\oqvajpbov\\or\"kgplwu", "mbzlfgpi\\\\zqcidjpzqdzxityxa", "lfkxvhma", "\xf2yduqzqr\"\\fak\"p\"n", "mpajacfuxotonpadvng", "anb\\telzvcdu\\a\xf2flfq", "lrs\"ebethwpmuuc\"\x86ygr", "qmvdbhtumzc\"ci", "meet", "yopg\x0fdxdq\"h\\ugsu\xffmolxjv", "uhy", "fzgidrtzycsireghazscvmwcfmw\\t", "cqohkhpgvpru", "bihyigtnvmevx\"xx", "xz", "zofomwotzuxsjk\"q\"mc\"js\"dnmalhxd", "\\ktnddux\\fqvt\"ibnjntjcbn", "ia", "htjadnefwetyp\xd5kbrwfycbyy", "\"\\hkuxqddnao", "meqqsz\x83luecpgaem", "cvks\x87frvxo\"svqivqsdpgwhukmju", "sgmxiai\\o\"riufxwjfigr\xdf", "fgywdfecqufccpcdn", "faghjoq\x28abxnpxj", "zuppgzcfb\"dctvp\"elup\"zxkopx", "xqs\x45xxdqcihbwghmzoa", "anbnlp\\cgcvm\"hc", "xf\"fgrngwzys", "nrxsjduedcy\x24", "\x71sxl\"gj\"sds\"ulcruguz\\t\\ssvjcwhi", "jhj\"msch", "qpovolktfwyiuyicbfeeju\x01", "nkyxmb\"qyqultgt\"nmvzvvnxnb", "ycsrkbstgzqb\"uv\\cisn", "s", "ueptjnn\"\"sh", "lp\"z\"d\"mxtxiy", "yzjtvockdnvbubqabjourf\"k\"uoxwle", "\x82\"wqm\"", "\xb5cwtuks\x5fpgh", "wd", "tbvf", "ttbmzdgn", "vfpiyfdejyrlbgcdtwzbnm", "uc", "otdcmhpjagqix", "\\\xb1qso\"s", "scowax", "behpstjdh\xccqlgnqjyz\"eesn", "r\xe1cbnjwzveoomkzlo\\kxlfouhm", "jgrl", "kzqs\\r", "ctscb\x7fthwkdyko\"\x62pkf\"d\xe6knmhurg", "tc\"kw\x3ftt", "bxb\x5ccl", "jyrmfbphsldwpq", "jylpvysl\"\"juducjg", "en\\m\"kxpq\"wpb\\\"", "madouht\"bmdwvnyqvpnawiphgac\"", "vuxpk\"ltucrw", "aae\x60arr", "ttitnne\"kilkrgssnr\xfdurzh", "oalw", "pc\"\"gktkdykzbdpkwigucqni\"nxiqx", "dbrsaj", "bgzsowyxcbrvhtvekhsh\"qgd", "kudfemvk\"\"\"hkbrbil\"chkqoa", "zjzgj\\ekbhyfzufy", "\\acos\"fqekuxqzxbmkbnn\x1ejzwrm", "elxahvudn\"txtmomotgw", "\x2eoxmwdhelpr\"cgi\xf7pzvb", "eapheklx", "hfvma\"mietvc\"tszbbm\"czex", "h\"iiockj\\\xc1et", "d\"rmjjftm", "qlvhdcbqtyrhlc\\", "yy\"rsucjtulm\"coryri\"eqjlbmk", "tv", "r\"bfuht\\jjgujp\"", "kukxvuauamtdosngdjlkauylttaokaj", "srgost\"\"rbkcqtlccu\x65ohjptstrjkzy", "yxwxl\\yjilwwxffrjjuazmzjs", "dxlw\\fkstu\"hjrtiafhyuoh\"sewabne", "\x88sj\"v", "rfzprz\xec\"oxqclu\"krzefp\\q", "cfmhdbjuhrcymgxpylllyvpni", "ucrmjvmimmcq\x88\xd9\"lz", "lujtt\"", "gvbqoixn\"pmledpjmo\"flydnwkfxllf", "dvxqlbshhmelsk\x8big\"l", "mx\x54lma\x8bbguxejg", "\x66jdati\xeceieo", "\"iyyupixei\x54ff", "xohzf\"rbxsoksxamiu", "vlhthspeshzbppa\x4drhqnohjop\"\"mfjd", "f\"tvxxla\"vurian\"\"idjq\x3aptm\xc3olep", "gzqz", "kbq\\wogye\\altvi\\hbvmodny", "j\xd8", "ofjozdhkblvndl", "hbitoupimbawimxlxqze", "ypeleimnme", "xfwdrzsc\\oxqamawyizvi\\y", "enoikppx\xa1ixe\"yo\"gumye", "fb", "vzf", "zxidr", "cu\x31beirsywtskq", "lxpjbvqzztafwezd", "\\jyxeuo\x18bv", "b\"vawc\"p\\\\giern\"b", "odizunx\"\"t\\yicdn\"x\"sdiz", "\"\"tebrtsi", "ctyzsxv\xa6pegfkwsi\"tgyltaakytccb", "htxwbofchvmzbppycccliyik\xe5a", "ggsslefamsklezqkrd", "rcep\"fnimwvvdx\"l", "zyrzlqmd\x12egvqs\\llqyie", "\x07gsqyrr\\rcyhyspsvn", "butg\"", "gb", "gywkoxf\"jsg\\wtopxvumirqxlwz", "rj\"ir\"wldwveair\x2es\"dhjrdehbqnzl", "ru\"elktnsbxufk\\ejufjfjlevt\\lrzd", "\"widsvok", "oy\"\x81nuesvw", "ay", "syticfac\x1cfjsivwlmy\"pumsqlqqzx", "m", "rjjkfh\x78cf\x2brgceg\"jmdyas\"\\xlv\xb6p", "tmuvo\"\x3ffdqdovjmdmkgpstotojkv\"as", "jd\\ojvynhxllfzzxvbn\"wrpphcvx", "pz", "\"twr", "n\\hdzmxe\"mzjjeadlz", "fb\"rprxuagvahjnri", "rfmexmjjgh\\xrnmyvnatrvfruflaqjnd", "obbbde\"co\"qr\"qpiwjgqahqm\\jjp\"", "vpbq\"\"y\"czk\\b\x52ed\"lnzepobp", "syzeajzfarplydipny\"y\"\xe8ad", "mpyodwb", "\x47rakphlqqptd", "wa\"oj\"aiy", "a", "ropozx", "q\x51nbtlwa", "etukvgx\\jqxlkq", "\"tp\"rah\"pg\"s\"bpdtes\\tkasdhqd", "dn\"qqpkikadowssb\xcah\"dzpsf\\ect\"jdh", "pxunovbbrrn\\vullyn\"bno\"\"\"myfxlp\"", "qaixyazuryvkmoulhcqaotegfj\\mpzm", "bvfrbicutzbjwn\\oml\"cf\"d\"ezcpv\"j", "rmbrdtneudemigdhelmb", "aq\\aurmbhy", "wujqvzw", "gf\"tssmvm\"gm\"hu\x9a\xb7yjawsa", "hrhqqxow\xe2gsydtdspcfqy\"zw\\ou", "ianwwf\\yko\\tdujhhqdi", "xylz\"zpvpab", "lwuopbeeegp", "aoop\x49jhhcexdmdtun", "\\\\mouqqcsgmz", "tltuvwhveau\x43b\"ymxjlcgiymcynwt", "gsugerumpyuhtjljbhrdyoj", "lnjm\xb8wg\"ajh", "zmspue\"nfttdon\\b\"eww", "\"w\x67jwaq\x7ernmyvs\\rmdsuwydsd\"th", "ogtgvtlmcvgllyv", "z\"fqi\"rvddoehrciyl", "yustxxtot\"muec\"xvfdbzunzvveq", "mqslw", "txqnyvzmibqgjs\xb6xy\x86nfalfyx", "kzhehlmkholov", "plpmywcnirrjutjguosh\\", "pydbnqofv\"dn\\m", "aegqof", "eambmxt\\dxagoogl\\zapfwwlmk", "afbmqitxxqhddlozuxcpjxgh", "vgts", "bfdpqtoxzzhmzcilehnflna", "s\"idpz", "\xcfhgly\"nlmztwybx\"ecezmsxaqw", "aackfgndqcqiy", "\x22unqdlsrvgzfaohoffgxzfpir\"s", "abh\"ydv\"kbpdhrerl", "bdzpg", "ekwgkywtmzp", "wtoodejqmrrgslhvnk\"pi\"ldnogpth", "njro\x68qgbx\xe4af\"\\suan")

  val input = Seq(""" "qxfcsmh" """, """ "ffsfyxbyuhqkpwatkjgudo" """, """ "byc\x9dyxuafof\\\xa6uf\\axfozomj\\olh\x6a" """, """ "jtqvz" """, """ "uzezxa\"jgbmojtwyfbfguz" """, """ "vqsremfk\x8fxiknektafj" """, """ "wzntebpxnnt\"vqndz\"i\x47vvjqo\"" """, """ "higvez\"k\"riewqk" """, """ "dlkrbhbrlfrp\\damiauyucwhty" """, """ "d\"" """, """ "qlz" """, """ "ku" """, """ "yy\"\"uoao\"uripabop" """, """ "saduyrntuswlnlkuppdro\\sicxosted" """, """ "tj" """, """ "zzphopswlwdhebwkxeurvizdv" """, """ "xfoheirjoakrpofles\"nfu" """, """ "q\xb7oh\"p\xce\"n" """, """ "qeendp\"ercwgywdjeylxcv" """, """ "dcmem" """, """ "\"i\x13r\"l" """, """ "ikso\xdcbvqnbrjduh\"uqudzki\xderwk" """, """ "wfdsn" """, """ "pwynglklryhtsqbno" """, """ "hcoj\x63iccz\"v\"ttr" """, """ "zf\x23\\hlj\\kkce\\d\\asy\"yyfestwcdxyfj" """, """ "xs" """, """ "m\"tvltapxdvtrxiy" """, """ "bmud" """, """ "k\"a" """, """ "b\"oas" """, """ "\"yexnjjupoqsxyqnquy\"uzfdvetqrc" """, """ "vdw\xe3olxfgujaj" """, """ "qomcxdnd\"\\cfoe\"" """, """ "fpul" """, """ "m\"avamefphkpv" """, """ "vvdnb\\x\\uhnxfw\"dpubfkxfmeuhnxisd" """, """ "hey\\" """, """ "ldaeigghlfey" """, """ "eure\"hoy\xa5iezjp\\tm" """, """ "yygb\"twbj\\r\"\x10gmxuhmp\"" """, """ "weirebp\x39mqonbtmfmd" """, """ "ltuz\\hs\"e" """, """ "ysvmpc" """, """ "g\x8amjtt\"megl\"omsaihifwa" """, """ "yimmm" """, """ "iiyqfalh" """, """ "cwknlaaf" """, """ "q\x37feg\xc6s\"xx" """, """ "uayrgeurgyp\\oi" """, """ "xhug\"pt\"axugllbdiggzhvy" """, """ "kdaarqmsjfx\xc3d" """, """ "\"vkwla" """, """ "d\"" """, """ "tmroz\"bvfinxoe\\mum\"wmm" """, """ "\"n\"bbswxne\\p\\yr\"qhwpdd" """, """ "skzlkietklkqovjhvj\xfe" """, """ "pbg\\pab\"bubqaf\"obzcwxwywbs\\dhtq" """, """ "xxjidvqh\"lx\\wu\"ij" """, """ "daef\x5fe\x5b\\kbeeb\x13qnydtboof" """, """ "ogvazaqy\"j\x73" """, """ "y" """, """ "n\"tibetedldy\\gsamm\"nwu" """, """ "wldkvgdtqulwkad" """, """ "dpmxnj" """, """ "twybw\"cdvf\"mjdajurokbce" """, """ "ru\"\\lasij\"i" """, """ "roc\\vra\\lhrm" """, """ "pbkt\x60booz\"fjlkc" """, """ "j\x4dytvjwrzt" """, """ "\\uiwjkniumxcs" """, """ "cbhm\"nexccior\"v\"j\"nazxilmfp\x47" """, """ "qdxngevzrlgoq" """, """ "\"lrzxftytpobsdfyrtdqpjbpuwmm\x9e" """, """ "mdag\x0asnck\xc2ggj\"slb\"fjy" """, """ "wyqkhjuazdtcgkcxvjkpnjdae" """, """ "aixfk\xc0iom\x21vueob" """, """ "dkiiakyjpkffqlluhaetires" """, """ "ysspv\"lysgkvnmwbbsy" """, """ "gy\"ryexcjjxdm\"xswssgtr" """, """ "s" """, """ "ddxv" """, """ "qwt\"\x27puilb\"pslmbrsxhrz" """, """ "qdg\xc9e\\qwtknlvkol\x54oqvmchn\\" """, """ "lvo" """, """ "b" """, """ "fk\"aa\"\"yenwch\\\\on" """, """ "srig\x63hpwaavs\\\x80qzk\"xa\"\xe6u\\wr" """, """ "yxjxuj\"ghyhhxfj\"\xa6qvatre" """, """ "yoktqxjxkzrklkoeroil" """, """ "\"jfmik\"" """, """ "smgseztzdwldikbqrh\"" """, """ "jftahgctf\"hoqy" """, """ "tcnhicr\"znpgckt\"ble" """, """ "vqktnkodh\"lo\"a\\bkmdjqqnsqr" """, """ "ztnirfzqq" """, """ "s" """, """ "xx" """, """ "iqj\"y\\hqgzflwrdsusasekyrxbp\\ad" """, """ "\\xzjhlaiynkioz\"\"bxepzimvgwt" """, """ "s\x36rbw" """, """ "mniieztwrisvdx" """, """ "atyfxioy\x2b\\" """, """ "irde\x85\x5cvbah\\jekw\"ia" """, """ "bdmftlhkwrprmpat\"prfaocvp" """, """ "w\\k" """, """ "umbpausy" """, """ "zfauhpsangy" """, """ "p\"zqyw" """, """ "wtztypyqvnnxzvlvipnq\"zu" """, """ "deicgwq\\oqvajpbov\\or\"kgplwu" """, """ "mbzlfgpi\\\\zqcidjpzqdzxityxa" """, """ "lfkxvhma" """, """ "\xf2yduqzqr\"\\fak\"p\"n" """, """ "mpajacfuxotonpadvng" """, """ "anb\\telzvcdu\\a\xf2flfq" """, """ "lrs\"ebethwpmuuc\"\x86ygr" """, """ "qmvdbhtumzc\"ci" """, """ "meet" """, """ "yopg\x0fdxdq\"h\\ugsu\xffmolxjv" """, """ "uhy" """, """ "fzgidrtzycsireghazscvmwcfmw\\t" """, """ "cqohkhpgvpru" """, """ "bihyigtnvmevx\"xx" """, """ "xz" """, """ "zofomwotzuxsjk\"q\"mc\"js\"dnmalhxd" """, """ "\\ktnddux\\fqvt\"ibnjntjcbn" """, """ "ia" """, """ "htjadnefwetyp\xd5kbrwfycbyy" """, """ "\"\\hkuxqddnao" """, """ "meqqsz\x83luecpgaem" """, """ "cvks\x87frvxo\"svqivqsdpgwhukmju" """, """ "sgmxiai\\o\"riufxwjfigr\xdf" """, """ "fgywdfecqufccpcdn" """, """ "faghjoq\x28abxnpxj" """, """ "zuppgzcfb\"dctvp\"elup\"zxkopx" """, """ "xqs\x45xxdqcihbwghmzoa" """, """ "anbnlp\\cgcvm\"hc" """, """ "xf\"fgrngwzys" """, """ "nrxsjduedcy\x24" """, """ "\x71sxl\"gj\"sds\"ulcruguz\\t\\ssvjcwhi" """, """ "jhj\"msch" """, """ "qpovolktfwyiuyicbfeeju\x01" """, """ "nkyxmb\"qyqultgt\"nmvzvvnxnb" """, """ "ycsrkbstgzqb\"uv\\cisn" """, """ "s" """, """ "ueptjnn\"\"sh" """, """ "lp\"z\"d\"mxtxiy" """, """ "yzjtvockdnvbubqabjourf\"k\"uoxwle" """, """ "\x82\"wqm\"" """, """ "\xb5cwtuks\x5fpgh" """, """ "wd" """, """ "tbvf" """, """ "ttbmzdgn" """, """ "vfpiyfdejyrlbgcdtwzbnm" """, """ "uc" """, """ "otdcmhpjagqix" """, """ "\\\xb1qso\"s" """, """ "scowax" """, """ "behpstjdh\xccqlgnqjyz\"eesn" """, """ "r\xe1cbnjwzveoomkzlo\\kxlfouhm" """, """ "jgrl" """, """ "kzqs\\r" """, """ "ctscb\x7fthwkdyko\"\x62pkf\"d\xe6knmhurg" """, """ "tc\"kw\x3ftt" """, """ "bxb\x5ccl" """, """ "jyrmfbphsldwpq" """, """ "jylpvysl\"\"juducjg" """, """ "en\\m\"kxpq\"wpb\\\"" """, """ "madouht\"bmdwvnyqvpnawiphgac\"" """, """ "vuxpk\"ltucrw" """, """ "aae\x60arr" """, """ "ttitnne\"kilkrgssnr\xfdurzh" """, """ "oalw" """, """ "pc\"\"gktkdykzbdpkwigucqni\"nxiqx" """, """ "dbrsaj" """, """ "bgzsowyxcbrvhtvekhsh\"qgd" """, """ "kudfemvk\"\"\"hkbrbil\"chkqoa" """, """ "zjzgj\\ekbhyfzufy" """, """ "\\acos\"fqekuxqzxbmkbnn\x1ejzwrm" """, """ "elxahvudn\"txtmomotgw" """, """ "\x2eoxmwdhelpr\"cgi\xf7pzvb" """, """ "eapheklx" """, """ "hfvma\"mietvc\"tszbbm\"czex" """, """ "h\"iiockj\\\xc1et" """, """ "d\"rmjjftm" """, """ "qlvhdcbqtyrhlc\\" """, """ "yy\"rsucjtulm\"coryri\"eqjlbmk" """, """ "tv" """, """ "r\"bfuht\\jjgujp\"" """, """ "kukxvuauamtdosngdjlkauylttaokaj" """, """ "srgost\"\"rbkcqtlccu\x65ohjptstrjkzy" """, """ "yxwxl\\yjilwwxffrjjuazmzjs" """, """ "dxlw\\fkstu\"hjrtiafhyuoh\"sewabne" """, """ "\x88sj\"v" """, """ "rfzprz\xec\"oxqclu\"krzefp\\q" """, """ "cfmhdbjuhrcymgxpylllyvpni" """, """ "ucrmjvmimmcq\x88\xd9\"lz" """, """ "lujtt\"" """, """ "gvbqoixn\"pmledpjmo\"flydnwkfxllf" """, """ "dvxqlbshhmelsk\x8big\"l" """, """ "mx\x54lma\x8bbguxejg" """, """ "\x66jdati\xeceieo" """, """ "\"iyyupixei\x54ff" """, """ "xohzf\"rbxsoksxamiu" """, """ "vlhthspeshzbppa\x4drhqnohjop\"\"mfjd" """, """ "f\"tvxxla\"vurian\"\"idjq\x3aptm\xc3olep" """, """ "gzqz" """, """ "kbq\\wogye\\altvi\\hbvmodny" """, """ "j\xd8" """, """ "ofjozdhkblvndl" """, """ "hbitoupimbawimxlxqze" """, """ "ypeleimnme" """, """ "xfwdrzsc\\oxqamawyizvi\\y" """, """ "enoikppx\xa1ixe\"yo\"gumye" """, """ "fb" """, """ "vzf" """, """ "zxidr" """, """ "cu\x31beirsywtskq" """, """ "lxpjbvqzztafwezd" """, """ "\\jyxeuo\x18bv" """, """ "b\"vawc\"p\\\\giern\"b" """, """ "odizunx\"\"t\\yicdn\"x\"sdiz" """, """ "\"\"tebrtsi" """, """ "ctyzsxv\xa6pegfkwsi\"tgyltaakytccb" """, """ "htxwbofchvmzbppycccliyik\xe5a" """, """ "ggsslefamsklezqkrd" """, """ "rcep\"fnimwvvdx\"l" """, """ "zyrzlqmd\x12egvqs\\llqyie" """, """ "\x07gsqyrr\\rcyhyspsvn" """, """ "butg\"" """, """ "gb" """, """ "gywkoxf\"jsg\\wtopxvumirqxlwz" """, """ "rj\"ir\"wldwveair\x2es\"dhjrdehbqnzl" """, """ "ru\"elktnsbxufk\\ejufjfjlevt\\lrzd" """, """ "\"widsvok" """, """ "oy\"\x81nuesvw" """, """ "ay" """, """ "syticfac\x1cfjsivwlmy\"pumsqlqqzx" """, """ "m" """, """ "rjjkfh\x78cf\x2brgceg\"jmdyas\"\\xlv\xb6p" """, """ "tmuvo\"\x3ffdqdovjmdmkgpstotojkv\"as" """, """ "jd\\ojvynhxllfzzxvbn\"wrpphcvx" """, """ "pz" """, """ "\"twr" """, """ "n\\hdzmxe\"mzjjeadlz" """, """ "fb\"rprxuagvahjnri" """, """ "rfmexmjjgh\\xrnmyvnatrvfruflaqjnd" """, """ "obbbde\"co\"qr\"qpiwjgqahqm\\jjp\"" """, """ "vpbq\"\"y\"czk\\b\x52ed\"lnzepobp" """, """ "syzeajzfarplydipny\"y\"\xe8ad" """, """ "mpyodwb" """, """ "\x47rakphlqqptd" """, """ "wa\"oj\"aiy" """, """ "a" """, """ "ropozx" """, """ "q\x51nbtlwa" """, """ "etukvgx\\jqxlkq" """, """ "\"tp\"rah\"pg\"s\"bpdtes\\tkasdhqd" """, """ "dn\"qqpkikadowssb\xcah\"dzpsf\\ect\"jdh" """, """ "pxunovbbrrn\\vullyn\"bno\"\"\"myfxlp\"" """, """ "qaixyazuryvkmoulhcqaotegfj\\mpzm" """, """ "bvfrbicutzbjwn\\oml\"cf\"d\"ezcpv\"j" """, """ "rmbrdtneudemigdhelmb" """, """ "aq\\aurmbhy" """, """ "wujqvzw" """, """ "gf\"tssmvm\"gm\"hu\x9a\xb7yjawsa" """, """ "hrhqqxow\xe2gsydtdspcfqy\"zw\\ou" """, """ "ianwwf\\yko\\tdujhhqdi" """, """ "xylz\"zpvpab" """, """ "lwuopbeeegp" """, """ "aoop\x49jhhcexdmdtun" """, """ "\\\\mouqqcsgmz" """, """ "tltuvwhveau\x43b\"ymxjlcgiymcynwt" """, """ "gsugerumpyuhtjljbhrdyoj" """, """ "lnjm\xb8wg\"ajh" """, """ "zmspue\"nfttdon\\b\"eww" """, """ "\"w\x67jwaq\x7ernmyvs\\rmdsuwydsd\"th" """, """ "ogtgvtlmcvgllyv" """, """ "z\"fqi\"rvddoehrciyl" """, """ "yustxxtot\"muec\"xvfdbzunzvveq" """, """ "mqslw" """, """ "txqnyvzmibqgjs\xb6xy\x86nfalfyx" """, """ "kzhehlmkholov" """, """ "plpmywcnirrjutjguosh\\" """, """ "pydbnqofv\"dn\\m" """, """ "aegqof" """, """ "eambmxt\\dxagoogl\\zapfwwlmk" """, """ "afbmqitxxqhddlozuxcpjxgh" """, """ "vgts" """, """ "bfdpqtoxzzhmzcilehnflna" """, """ "s\"idpz" """, """ "\xcfhgly\"nlmztwybx\"ecezmsxaqw" """, """ "aackfgndqcqiy" """, """ "\x22unqdlsrvgzfaohoffgxzfpir\"s" """, """ "abh\"ydv\"kbpdhrerl" """, """ "bdzpg" """, """ "ekwgkywtmzp" """, """ "wtoodejqmrrgslhvnk\"pi\"ldnogpth" """, """ "njro\x68qgbx\xe4af\"\\suan" """)
  // val input = Seq(""" "" """, """ "abc" """, """ "aaa\"aaa" """, """ "\x27" """)

  def count1(xs: String) = xs.trim.length

  def count2(xs: String) = StringContext.treatEscapes(xs.trim.replaceAll("""\\x[0-9a-fA-F]{2}""", "!")).length - 2

  def count3(xs: String) = StringEscapeUtils.escapeJava(xs.trim).length + 2

  val sum = input map { line => count3(line) - count1(line) } sum

  println (sum)

}


object Day20Part2  {

  def divisorSum(n: Int) = 
    (((n-1) / 50) to n+1) filter { i => (n%i) == 0 } sum

  val min = 706640 // for min, the sum of divisor is certainly lower than that number

  val res = Stream.from(min).takeWhile( i => divisorSum(i) < 3272728 ) last

  println (res)

}


object Day20 {

  import math._

  val gamma = 0.57721566490153286060651209008240243104215933593992

  def f(n: Int) = pow(E, gamma) * n * log(log(n))

  val stream = Stream.from(1).takeWhile( i => f(i) < 3272727 )

  stream foreach println

  val min = 775260 // for min, the sum of divisor is certainly lower than that number

  def divisorSum(n: Int) = 
    (1 to n+1) filter { i => (n%i) == 0 } sum

  val res = Stream.from(min).takeWhile( i => divisorSum(i) < 3600000 ) last

  println (res)


}