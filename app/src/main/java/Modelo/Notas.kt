package Modelo

abstract class Notas(var id:Int, var fecha:String, var hora:String, var asunto:String)
/* Esta clase es abstracta ya que nunca la vamos a instanciar, siempre vamos a usar a sus clases
   a sus clases hijas, DeTareas, DeTexto*/