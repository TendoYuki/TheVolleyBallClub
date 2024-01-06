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

        /**
         * Regex that parses the uri by capturing until the first match of one the following case is met :
         * - '/?'
         * - '?'
         * - The last character of the string is '/'
         * If up to no match is found among the previous list of capture,
         * returns the whole uri, meaning that it was already parsed
         * 
         * e.g. 
         * Given the uris :
         * - /planning/view?id=2/
         * - /planning/view?id=2
         * - /planning/view/?id=2/
         * - /planning/view/?id=2
         * - /planning/view/
         * - /planning/view
         * Will always return : /planning/view
         */
        $URI_PARSER_REGEX = "/((.*)(?=(?:\/\?))|(.*)(?=\?)|(?:(.*)(?=\/$))|(?:(.*)))/";
        
        $matches = array();
        preg_match($URI_PARSER_REGEX, $uri, $matches);

        // Extracts the first capture group from the matches
        $request = $matches[1];

        return $request;
    }

    session_start();
?>