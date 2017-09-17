package com.marco.tutorial

trait Ord {
  
  def <  (that:Any):Boolean
  def <= (that:Any):Boolean = (this < that) || (this == that)
  def >  (that:Any):Boolean = !(this <= that)
  def >= (that:Any):Boolean = !(this < that)
  
}