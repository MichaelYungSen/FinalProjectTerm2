/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package applog;

public enum option
{
  Admin,  karyawan;
  
  private option() {}
  
  public String value()
  {
    return name();
  }
  
  public static option fromvalue(String v)
  {
    return valueOf(v);
  }
}
