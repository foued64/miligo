/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/* global PF */

$(function()
{
    PF('selectRetour').jq.change(function()
    {
        if(PF('selectRetour').isChecked())
        {
            PF('calendarRetour').jq.show();
        }else
        {
            PF('calendarRetour').jq.hide();
        }
    });
});