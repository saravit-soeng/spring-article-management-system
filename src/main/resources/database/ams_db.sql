PGDMP     -                    x            ams_db    9.6.18    9.6.18     [           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                       false            \           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                       false            ]           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                       false            ^           1262    16434    ams_db    DATABASE     �   CREATE DATABASE ams_db WITH TEMPLATE = template0 ENCODING = 'UTF8' LC_COLLATE = 'English_United States.1252' LC_CTYPE = 'English_United States.1252';
    DROP DATABASE ams_db;
             postgres    false                        2615    2200    public    SCHEMA        CREATE SCHEMA public;
    DROP SCHEMA public;
             postgres    false            _           0    0    SCHEMA public    COMMENT     6   COMMENT ON SCHEMA public IS 'standard public schema';
                  postgres    false    3                        3079    12387    plpgsql 	   EXTENSION     ?   CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;
    DROP EXTENSION plpgsql;
                  false            `           0    0    EXTENSION plpgsql    COMMENT     @   COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';
                       false    1            �            1259    16435    article    TABLE       CREATE TABLE public.article (
    id integer NOT NULL,
    title character varying NOT NULL,
    description text,
    thumbnail character varying,
    category_id integer,
    author character varying(30),
    created_date timestamp without time zone DEFAULT now()
);
    DROP TABLE public.article;
       public         postgres    false    3            �            1259    16442    article_id_seq    SEQUENCE     w   CREATE SEQUENCE public.article_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 %   DROP SEQUENCE public.article_id_seq;
       public       postgres    false    3    185            a           0    0    article_id_seq    SEQUENCE OWNED BY     A   ALTER SEQUENCE public.article_id_seq OWNED BY public.article.id;
            public       postgres    false    186            �            1259    16444    category    TABLE     Z   CREATE TABLE public.category (
    id integer NOT NULL,
    name character varying(50)
);
    DROP TABLE public.category;
       public         postgres    false    3            �            1259    16447    category_id_seq    SEQUENCE     x   CREATE SEQUENCE public.category_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 &   DROP SEQUENCE public.category_id_seq;
       public       postgres    false    3    187            b           0    0    category_id_seq    SEQUENCE OWNED BY     C   ALTER SEQUENCE public.category_id_seq OWNED BY public.category.id;
            public       postgres    false    188            �           2604    16449 
   article id    DEFAULT     h   ALTER TABLE ONLY public.article ALTER COLUMN id SET DEFAULT nextval('public.article_id_seq'::regclass);
 9   ALTER TABLE public.article ALTER COLUMN id DROP DEFAULT;
       public       postgres    false    186    185            �           2604    16450    category id    DEFAULT     j   ALTER TABLE ONLY public.category ALTER COLUMN id SET DEFAULT nextval('public.category_id_seq'::regclass);
 :   ALTER TABLE public.category ALTER COLUMN id DROP DEFAULT;
       public       postgres    false    188    187            U          0    16435    article 
   TABLE DATA               g   COPY public.article (id, title, description, thumbnail, category_id, author, created_date) FROM stdin;
    public       postgres    false    185   &       c           0    0    article_id_seq    SEQUENCE SET     =   SELECT pg_catalog.setval('public.article_id_seq', 23, true);
            public       postgres    false    186            W          0    16444    category 
   TABLE DATA               ,   COPY public.category (id, name) FROM stdin;
    public       postgres    false    187   C       d           0    0    category_id_seq    SEQUENCE SET     =   SELECT pg_catalog.setval('public.category_id_seq', 4, true);
            public       postgres    false    188            �           2606    16453    article article_pkey 
   CONSTRAINT     R   ALTER TABLE ONLY public.article
    ADD CONSTRAINT article_pkey PRIMARY KEY (id);
 >   ALTER TABLE ONLY public.article DROP CONSTRAINT article_pkey;
       public         postgres    false    185    185            �           2606    16455    category category_pkey 
   CONSTRAINT     T   ALTER TABLE ONLY public.category
    ADD CONSTRAINT category_pkey PRIMARY KEY (id);
 @   ALTER TABLE ONLY public.category DROP CONSTRAINT category_pkey;
       public         postgres    false    187    187            �           2606    16456    article fk_category_id    FK CONSTRAINT     |   ALTER TABLE ONLY public.article
    ADD CONSTRAINT fk_category_id FOREIGN KEY (category_id) REFERENCES public.category(id);
 @   ALTER TABLE ONLY public.article DROP CONSTRAINT fk_category_id;
       public       postgres    false    187    2014    185            U      x������ � �      W      x������ � �     