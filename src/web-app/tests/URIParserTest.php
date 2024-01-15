<?php

use PHPUnit\Framework\TestCase;

use Routing\URIParser;

final class URIParserTest extends TestCase {
    public function testParseURI() {
        $this->assertEquals("/planning/view",URIParser::parseURI("/planning/view/?id=2/"));
        $this->assertEquals("/planning/view",URIParser::parseURI("/planning/view/?id=2"));
        $this->assertEquals("/planning/view",URIParser::parseURI("/planning/view?id=2/"));
        $this->assertEquals("/planning/view",URIParser::parseURI("/planning/view?id=2"));
        $this->assertEquals("/planning/view",URIParser::parseURI("/planning/view/"));
        $this->assertEquals("/planning/view",URIParser::parseURI("/planning/view/"));
    }
}