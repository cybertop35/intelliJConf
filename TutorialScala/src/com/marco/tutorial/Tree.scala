package com.marco.tutorial

abstract class Tree {
  
  case class Sum(l:Tree, r:Tree) extends Tree;
  case class Var (n:String) extends Tree;
  case class Const (v:Int) extends Tree;
  
  type Environment = String => Int;
  
  def eval(t: Tree, env: Environment): Int = t match {
      case Sum(l, r) => eval(l, env) + eval(r, env)
      case Var(n) => env(n);
      case Const(v) => v;
  }
  
  def derive(t: Tree, v: String): Tree = t match {
    case Sum(l, r) => Sum(derive(l, v), derive(r, v))
    case Var(n) if (v == n) => Const(1)
    case _ => Const(0)
  }
  
  /*
   *  case :
   *  	- non serve "new" per creare un instanza 
   *   	- get e set auto-definiti per i parametri del costruttore
   *    -  le istanze di queste classi possono essere decomposte con il pattern matching
	 *			 come vedremo più avanti.
   * 
   */
  
  

}