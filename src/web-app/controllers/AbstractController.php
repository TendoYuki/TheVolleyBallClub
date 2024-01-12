<?php

namespace Controllers;

/**
 * Abstract controller 
 */
abstract class AbstractController {
    abstract public static function new();
    abstract public static function delete();
    abstract public static function edit();
}