<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<html lang="en">
<head>
    <title>Загрузка данных</title>
    <script src="./js/jquery-1.12.4.min.js"></script>
    <link href="./css/bootstrap.min.css" rel="stylesheet"/>
    <script src="./js/script.js" type="text/javascript"></script>
    <link rel="stylesheet" href="./css/fileinput.min.css"/>
    <script src="./js/fileinput.min.js"></script>
    <script src="./js/fileinput_locale_ru.js"></script>
    <script src="./js/bootstrap.min.js" type="text/javascript"></script>
    <script src="./js/bootbox.min.js" type="text/javascript"></script>
    <script type="text/javascript">
        $(document).ready(function () {
            var fileloaded = false;


            $("#atmosphereFile").fileinput({
                language: "ru",
                dropZoneEnabled: false,
                showPreview: false,
                showUploadedThumbs: false,
                showUpload: false,
                showRemove: false,
                uploadUrl: window.location.href + "/atmosphere/save",
                maxFileCount: 1,
                allowedFileExtensions: ["csv", "txt","CSV", "TXT"]
            }).on('filebatchuploadsuccess', function(event, data) {
                alert('Файл загружен');
            }).on('filebatchuploaderror', function(event, data) {
                console.log('Ошибка при загрузке');
            });


            $('#atmosphereFile').on('fileloaded', function (event, file) {
                var title = file.name;
                $('#atmosphereFile').fileinput('upload');
                $('.kv-fileinput-caption').text(title);
                fileloaded = true;
            });

            $('#atmosphereFile').on('fileuploaderror', function (event, data) {
                if (data.files[0].type.toLowerCase().indexOf("csv") === -1 && data.files[0].type.toLowerCase().indexOf("txt") === -1) {
                    var message = "Возможные типы файлов: csv, txt";
                    $('.file-caption-name').attr("title", message);
                    $('.kv-fileinput-caption').text(message);
                    $('.file-caption-ellipsis').text(message);
                    fileloaded = false;
                }
            });


            $("#radiationFile").fileinput({
                language: "ru",
                dropZoneEnabled: false,
                showPreview: false,
                showUploadedThumbs: false,
                showUpload: false,
                showRemove: false,
                uploadAsync: false,
                uploadUrl: window.location.href + "/radiation/save",
                maxFileCount: 1,
                allowedFileExtensions: ["csv", "txt","CSV", "TXT"]
            }).on('filebatchuploadsuccess', function(event, data) {
                alert('Файл загружен');
            }).on('filebatchuploaderror', function(event, data) {
                console.log('Ошибка при загрузке');
            });


            $('#radiationFile').on('fileloaded', function (event, file) {
                var title = file.name;
                $('#radiationFile').fileinput('upload');
                $('.kv-fileinput-caption').text(title);
                fileloaded = true;
            });

            $('#radiationFile').on('fileuploaderror', function (event, data) {
                if (data.files[0].type.toLowerCase().indexOf("csv") === -1 && data.files[0].type.toLowerCase().indexOf("txt") === -1) {
                    var message = "Возможные типы файлов: csv, txt";
                    $('.file-caption-name').attr("title", message);
                    $('.kv-fileinput-caption').text(message);
                    $('.file-caption-ellipsis').text(message);
                    fileloaded = false;
                }
            });


            $("#ostankinoFile").fileinput({
                language: "ru",
                dropZoneEnabled: false,
                showPreview: false,
                showUploadedThumbs: false,
                showUpload: false,
                showRemove: false,
                uploadAsync: false,
                uploadUrl: window.location.href + "/ostankino/save",
                maxFileCount: 1,
                allowedFileExtensions: ["csv", "txt","CSV", "TXT"]
            }).on('filebatchuploadsuccess', function(event, data) {
                alert('Файл загружен');
            }).on('filebatchuploaderror', function(event, data) {
                console.log('Ошибка при загрузке');
            });


            $('#ostankinoFile').on('fileloaded', function (event, file) {
                var title = file.name;
                $('#ostankinoFile').fileinput('upload');
                $('.kv-fileinput-caption').text(title);
                fileloaded = true;
            });

            $('#ostankinoFile').on('fileuploaderror', function (event, data) {
                if (data.files[0].type.toLowerCase().indexOf("csv") === -1 && data.files[0].type.toLowerCase().indexOf("txt") === -1) {
                    var message = "Возможные типы файлов: csv, txt";
                    $('.file-caption-name').attr("title", message);
                    $('.kv-fileinput-caption').text(message);
                    $('.file-caption-ellipsis').text(message);
                    fileloaded = false;
                }
            });

            $('.ajax-upload-dragdrop').each(function () {
                var realtext = $(this).find('span').html();
                var newtext = realtext.replace("Drag &amp; Drop Files", "Загрузите файл данных");
                $(this).find('span').html(newtext);
                $(this).css('width', '388px');

            });
            $(document.getElementsByClassName('ajax-file-upload-statusb')).css('width', '388px');
            $('.ajax-file-upload').each(function () {
                $(this).find('div').first().css("position", "relative").css("top", "50%").css("transform", "translateY(-50%)");
            });

            $(".ostankinoFile").hide();
            $(".radiationFile").hide();

            $("#source").on("change", function(){
                if ( $("#source").val()=="atmosphere"){
                    $(".ostankinoFile").hide();
                    $(".radiationFile").hide();
                    $(".atmosphereFile").show();
                }
                else if ($("#source").val()=="radiation"){
                    $(".ostankinoFile").hide();
                    $(".atmosphereFile").hide();
                    $(".radiationFile").show();
                }
                else {
                    $(".atmosphereFile").hide();
                    $(".radiationFile").hide();
                    $(".ostankinoFile").show();
                }
            })

        })
    </script>
</head>
<body>
<div class="container-fluid" style="padding-top: 10px;">
    <legend>Загрузка данных</legend>

    <c:choose>
        <c:when test="${result!=null && !result}">
            <div class="row">
                <div class="alert alert-danger col-sm-6" role="alert">
                        Произошла ошибка при обработке данных
                </div>
            </div>
        </c:when>
        <c:when test="${result!=null && result}">
            <div class="row">
                <div class="alert alert-success col-sm-6" role="alert">
                        Данные успешно записаны
                </div>
            </div>
        </c:when>
    </c:choose>
    <div class="col-sm-6">
        <div class="well" method="get">
            <div class="form-group">
                <label for="source">Тип данных</label>
                <select name="source" id="source" class="combobox form-control">
                    <option selected value="atmosphere">Температурная стратификация</option>
                    <option value="radiation">Солнечная радиация</option>
                    <option value="ostankino">Данные Останкино</option>
                </select>
            </div>
            <div class="form-group atmosphereFile">
                <label for="atmosphereFile">Файл</label>
                <input id="atmosphereFile" class="input-sm form-control" type="file" class="file" name="atmosphereFile">
            </div>
            <div class="form-group radiationFile">
                <label for="radiationFile">Файл</label>
                <input id="radiationFile" class="input-sm form-control" type="file" class="file" name="radiationFile">
            </div>
            <div class="form-group ostankinoFile">
                <label for="ostankinoFile">Файл</label>
                <input id="ostankinoFile" class="input-sm form-control" type="file" class="file" name="ostankinoFile">
            </div>
        </div>
    </div>

</div>
</body>
</html>
