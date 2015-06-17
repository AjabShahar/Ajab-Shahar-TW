<?php
$SITE_ROOT = "http://localhost";

$jsonData = getData($SITE_ROOT);
makePage($jsonData, $SITE_ROOT);
function getData($siteRoot) {
    $id = ctype_digit($_GET['id']) ? $_GET['id'] : 1;
    $rawData = http_get($siteRoot.'/api/songs/'.$id, array('headers' => array('content_type' => 'application/json')), $info);
    $res =  http_parse_message($rawData)->body;
    return json_decode($res);
}

function makePage($data, $siteRoot) {
    ?>
    <!DOCTYPE html>
    <html>
        <head>
            <meta property="og:title" content="<?php echo $data->songTitle->englishTransliteration; ?>" />
            <meta property="og:description" content="<?php echo $data->about; ?>" />
            <meta property="og:image" content="<?php echo $data->thumbnailURL; ?>" />

        </head>
        <body>
            <p><?php echo $data->about; ?></p>
            <img src="<?php echo $thumbnailURL; ?>">
        </body>
    </html>
<?php
}