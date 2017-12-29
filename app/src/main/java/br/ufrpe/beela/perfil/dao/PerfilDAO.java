package br.ufrpe.beela.perfil.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import br.ufrpe.beela.database.dao.BD;
import br.ufrpe.beela.perfil.dominio.PerfilComida;
import br.ufrpe.beela.perfil.dominio.PerfilMusica;
import br.ufrpe.beela.perfil.dominio.PerfilUsuario;
import br.ufrpe.beela.usuario.dominio.Usuario;

/**
 * Created by vidal on 05/12/2017.
 *  modificado por vidal 14/12
 *  modificado por vidal 07/12
 */

public class PerfilDAO {
    private SQLiteDatabase bd;
    public PerfilDAO(Context context, String tipo){
        BD auxBd = new BD(context);
        if(tipo.equals("R")){ bd = auxBd.getReadableDatabase();}
        else{bd = auxBd.getWritableDatabase();}
    }
 //TODO   =========================================================================================================
    public void inserirPerfil(PerfilUsuario perfil) {
        ContentValues valores = new ContentValues();
        valores.put("id_usuario",perfil.getId_Usuario());
        valores.put("nome_perfil", perfil.getNome());
        bd.insert("perfilUsuario", null, valores);
        bd.close();
    }
    public void inserirPerfilMusica(PerfilMusica musica){
            ContentValues valores = new ContentValues();
            valores.put("nome",musica.getNome());
            valores.put("id_usuario",musica.getId_usuario());
            valores.put("nome_perfil",musica.getNome_perfil());
            bd.insert("perfilMusica",null,valores);
            bd.close();
    }


    public void inserirPerfilComida(PerfilComida comida){
            ContentValues valores = new ContentValues();
            valores.put("nome",comida.getNome());
            valores.put("id_usuario",comida.getId_usuario());
            valores.put("nome_perfil",comida.getNome_perfil());
            bd.insert("perfilComida",null,valores);
            bd.close();
    }

//TODO ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    public void updatePerfil(PerfilUsuario perfil) {
        String where = "id_usuario = " + perfil.getId_Usuario()+" AND nome_perfil = "+ perfil.getNome();
        ContentValues valores = new ContentValues();
//        valores.put("comida", perfil.getComida());
//        valores.put("musica", perfil.getMusica());
//        valores.put("esporte", usuario.getPerfil().getEsporte());

        bd.update("perfilUsuario", valores, where,null);
        bd.close();
    }


    public void deletePerfilUsuario(PerfilUsuario perfil) {
        String where = "id_usuario = " + perfil.getId_Usuario()+" AND nome_perfil = "+ perfil.getNome();
        bd.delete("perfilUsuario", where , null);
        bd.close();}

    public void deletePerfilMusica(PerfilMusica musica) {
        String where = "id_usuario = " + musica.getId_usuario()+" AND nome_perfil = "+ musica.getNome_perfil();
        bd.delete("perfilComida", where , null);
        bd.close();}

    public void deletePerfilComida(PerfilComida comida) {
        String where = "id_usuario = " + comida.getId_usuario()+" AND nome_perfil = "+ comida.getNome_perfil();
        bd.delete("perfilComida", where , null);
        bd.close();}

    public boolean buscarPerfil(Usuario usuario, String NomedoPerfil){
        String where ="SELECT * FROM perfilUsuario WHERE id_usuario = " + usuario.getId()+" AND nome_perfil = "+ NomedoPerfil;
        Cursor cursor = bd.rawQuery(where , null);
        try{
        cursor.moveToNext();
        return true;}
        catch(Exception e ){return false;}
        finally {bd.close();}
    }
    public ArrayList<PerfilComida> sqlGetPerfilComida (PerfilUsuario perfilUsuario) {
        ArrayList<PerfilComida> comidas = new ArrayList<PerfilComida>();
        String where = "SELECT * FROM perfilComida WHERE id_usuario = '" + perfilUsuario.getId_Usuario() + "' AND nome_perfil = '" + perfilUsuario.getNome() + "'";
        Cursor cursor = bd.rawQuery(where, null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            do {
                PerfilComida comida = new PerfilComida();
                comida.setId(cursor.getInt(0));
                comida.setNome(cursor.getString(1));
                comida.setId_usuario(cursor.getInt(2));
                comida.setNome_perfil(cursor.getString(3));
                comidas.add(comida);
            } while (cursor.moveToNext());
        }
        bd.close();
        return comidas;
    }

    public ArrayList<PerfilMusica> sqlGetPerfilMusica (PerfilUsuario perfilUsuario) {
        ArrayList<PerfilMusica> musicas = new ArrayList<PerfilMusica>();
        String where = "SELECT * FROM perfilMusica WHERE id_usuario = '" + perfilUsuario.getId_Usuario() + "' AND nome_perfil = '" + perfilUsuario.getNome() + "'";
        Cursor cursor = bd.rawQuery(where, null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            do {
                PerfilMusica musica = new PerfilMusica();
                musica.setId(cursor.getInt(0));
                musica.setNome(cursor.getString(1));
                musica.setId_usuario(cursor.getInt(2));
                musica.setNome_perfil(cursor.getString(3));
                musicas.add(musica);
            } while (cursor.moveToNext());
        }
        bd.close();
        return musicas;
    }


    public  ArrayList<PerfilUsuario> sqlGetPerfil(Usuario usuario){
        ArrayList<PerfilUsuario> list = new ArrayList<PerfilUsuario>();
        String where ="SELECT * FROM perfilUsuario WHERE id_usuario = '"+ usuario.getId()+"'";
        Cursor cursor = bd.rawQuery(where,null);

        if (cursor.getCount()>0){
            cursor.moveToFirst();
            do {
                PerfilUsuario p = new PerfilUsuario();
                p.setId(cursor.getInt(0));
                p.setId_usuario(cursor.getInt(1));
                p.setNome(cursor.getString(2));
//                p.setComida(cursor.getString(3));
//                p.setMusica(cursor.getString(4));
                //p.setEsporte(cursor.getString(5));
                list.add(p);
            } while(cursor.moveToNext());
        }
        bd.close();
        return list;
    }
}
