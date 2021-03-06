<?php
$SITE_ROOT = "http://localhost";

$jsonData = getData($SITE_ROOT);
makePage($jsonData, $SITE_ROOT);
function getData($siteRoot) {
    $id = ctype_digit($_GET['id']) ? $_GET['id'] : 1;
    $rawData = http_get($siteRoot.'/api/reflections/edit?id='.$id, array('headers' => array('content_type' => 'application/json')), $info);
    $res =  http_parse_message($rawData)->body;
    $result = json_decode($res);

    if (strpos($result->thumbnailURL,'http') === false) {
        $result->thumbnailURL = "http://www.ajabshahar.com" . $result->thumbnailURL;
    }

    return $result;
}

function makePage($data, $siteRoot) {
    ?>
    <!DOCTYPE html>
    <html>
        <head>
            <meta property="og:title" content="<?php echo $data->title; ?>" />
            <meta property="og:description" content="<?php echo $data->about; ?>" />
            <meta property="og:image" content="<?php echo $data->thumbnailURL; ?>" />

        </head>
        <body>
        </body>
    </html>
<?php
}