/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/* Permet l'affichage du formulaire de mise en indispo*/
/* global PF */
$(function()
{
   
        if(document.getElementById('etatSelectionne').get)
        {
            document.getElementById('panneauIndispo').style["display"] = "block";
        }else
        {
            document.getElementById('panneauIndispo').style["display"] = "none";
        }
});