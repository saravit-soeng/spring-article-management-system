"use strict";

var updatedArticle = null;
var articles = null;

$(document).ready(function () {
    getArticles();

    $('#save-article').click(function () {
        let data = $('#thumbnail')[0].files[0];
        let formData = new FormData();
        formData.append("file", data);
        $.ajax({
            url: '/api/upload',
            type: 'POST',
            enctype: 'multipart/form-data',
            headers:{
                authorization:"Basic YXBpdXNlcjphcGlAMTIzNA=="
            },
            contentType: false,
            processData: false,
            data: formData,
            headers:{
                authorization:"Basic YXBpdXNlcjphcGlAMTIzNA=="
            },
            success:function (response) {
                addArticle(response.data)
            },
            error: function (err) {
                console.log(err);
            }

        });
    });

    $('#update-article').click(function () {
        let updatedData = {
            id: updatedArticle.id,
            title: $('#title-update').val(),
            description: $('#description-update').val(),
            author: $('#author-update').val(),
            thumbnail: updatedArticle.thumbnail,
            category_id: parseInt($('#category-update').val())
        }
        $.ajax({
            url:"/api/article",
            type:"PUT",
            contentType: 'application/json',
            headers:{
                authorization:"Basic YXBpdXNlcjphcGlAMTIzNA=="
            },
            data: JSON.stringify(updatedData),
            success:function (response) {
                console.log(response.message);
                getArticles();
            },
            error:function (err) {
                console.log(err);
            }
        });
    });
});


function showEditedArticle(id) {
    $.ajax({
        url:"/api/category",
        type:"GET",
        headers:{
            authorization:"Basic YXBpdXNlcjphcGlAMTIzNA=="
        },
        success: function (response) {
            let categories = response.data;
            let element = '';
            categories.forEach(function (category) {
                element += '<option value="'+category.id+'">'+category.name+'</option>'
            })
            $('#category-update').empty();
            $('#category-update').append(element);

            let filterArticle = articles.filter(function (article) {
                return article.id === id;
            });

            updatedArticle = filterArticle[0];

            $('#title-update').val(updatedArticle.title);
            $('#category-update').val(updatedArticle.category.id);
            $('#author-update').val(updatedArticle.author);
            $('#description-update').val(updatedArticle.description);
        },
        error: function (err) {
            console.log(err);
        }
    });
}

function deleteArticle(id) {
    $.ajax({
        url:"/api/article/"+id,
        type:"DELETE",
        headers:{
            authorization:"Basic YXBpdXNlcjphcGlAMTIzNA=="
        },
        success:function (response) {
            getArticles();
        },
        error:function (err) {
            console.log(err);
        }
    })
}

function viewArticle(id) {
    $('#detail-view').empty();
    let filterArticle = articles.filter(function (article) {
        return article.id === id;
    });
    const article = filterArticle[0];
    let date = new Date(article.created_date);
    $('#title-view').text(article.title);
    let detail = '<img src="'+article.thumbnail+'" style="width: 470px;height: 300px"/>';
    detail += '<p>'+date.toLocaleString()+'</p>';
    detail += article.description;
    detail += '<br/><br/><p>Written by: <strong>'+article.author+'</strong></p>';
    $('#detail-view').append(detail);
}

function addArticle(thumbnail) {
    let data = {
        title: $('#title').val(),
        description: $('#description').val(),
        author: $('#author').val(),
        thumbnail: thumbnail,
        category_id: parseInt($('#category').val())
    }
    console.log(data);
    $.ajax({
        url:"/api/article",
        type:"POST",
        contentType: 'application/json',
        headers:{
            authorization:"Basic YXBpdXNlcjphcGlAMTIzNA=="
        },
        data: JSON.stringify(data),
        success:function (response) {
            console.log(response.message);
            getArticles();
            clearControls();
        },
        error:function (err) {
            console.log(err);
        }
    });
}

function getArticles() {
    $.ajax({
        url:"/api/article?page=1&limit=30",
        type:"GET",
        headers:{
            authorization:"Basic YXBpdXNlcjphcGlAMTIzNA=="
        },
        success: function (response) {
            $('#article-list').empty();
            articles = response.data;
            let table = '<table class="table"><thead><th>Title</th><th>Category</th><th>Author</th><th>Thumbnail</th><th>Action</th></thead><tbody>';
            articles.forEach(function (article) {
                table += '<tr><td>'+article.title+'</td><td>'+article.category.name+'</td><td>'+article.author+'</td><td><img src="'+article.thumbnail+
                    '" width="50px" height="40px"></td><td>'+
                    '<button class="btn btn-info" data-toggle="modal" data-target="#viewArticleModal" onclick="viewArticle('+article.id+')"><i class="fa fa-eye" aria-hidden="true"></i></button> '+
                    '<button class="btn btn-primary" data-toggle="modal" data-target="#updateArticleModal" onclick="showEditedArticle('+article.id+')"><i class="fa fa-edit" aria-hidden="true"></i></button> '+
                    '<button class="btn btn-danger" onclick="deleteArticle('+article.id+')"><i class="fa fa-trash" aria-hidden="true"></i></button></td></tr>';
            })
            table += '</tbody></table>';
            $('#article-list').append(table);
        },
        error: function (err) {
            console.log(err);
        }
    });
}

function getCategories() {
    $.ajax({
        url:"/api/category",
        type:"GET",
        headers:{
            authorization:"Basic YXBpdXNlcjphcGlAMTIzNA=="
        },
        success: function (response) {
            let categories = response.data;
            let element = '<option selected="true" disabled="disabled" value="0">Choose Category</option>';
            categories.forEach(function (category) {
                element += '<option value="'+category.id+'">'+category.name+'</option>'
            })
            $('#category').empty();
            $('#category').append(element);
        },
        error: function (err) {
            console.log(err);
        }
    });
}

function clearControls() {
    $('#title').val("");
    $('#description').val("");
    $('#author').val("");
    $('#thumbnail').val("");
    $('#category').val(0);
}