<?php
    if ( !defined('ABSPATH') )
    define('ABSPATH', dirname(__FILE__) . '/../');

    if ( !defined('COMPONENTS') )
    define('COMPONENTS', ABSPATH.'components'.'/');

    if ( !defined('PAGES') )
    define('PAGES', ABSPATH.'pages'.'/');

    if ( !defined('MODELS') )
    define('MODELS', ABSPATH.'models'.'/');

    if ( !defined('TEMPLATES') )
    define('TEMPLATES', ABSPATH.'templates'.'/');


    if ( !defined('PUBLIC_PATH') )
    define('PUBLIC_PATH', ABSPATH.'public'.'/');

    /**
     * Gets a file from the public repository
     * @param string $file Path of the file from the public directory
     * @return string Content of the file
     */
    function get_public_file($file) {
        return file_get_contents(PUBLIC_PATH.$file);
    }

    /**
     * Parses the given URI by removing all trailing "/", all GET parameters 
     * and returns it
     * @param string $uri URI to parse
     * @return string Parsed URI
     */
    function parse_uri($uri) {
        $request = $uri;
        if(substr($uri, -1) == '/') $request = substr($uri,0,-1);
        return trim(strtok($request, '?'));
    }

    session_start();
?>