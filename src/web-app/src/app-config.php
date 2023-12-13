<?php
    if ( !defined('ABSPATH') )
    define('ABSPATH', dirname(__FILE__) . '/');

    if ( !defined('COMPONENTS') )
    define('COMPONENTS', ABSPATH.'components'.'/');

    if ( !defined('PAGES') )
    define('PAGES', ABSPATH.'pages'.'/');

    if ( !defined('PUBLIC_PATH') )
    define('PUBLIC_PATH', ABSPATH.'public'.'/');

    session_start();
?>