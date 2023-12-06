<?php require_once("../../app-config.php") ?>

<script src="/PHP/SAE-Volley/components/carrousel/carrousel.js" defer></script>

<?php
    class Carrousel{
        private $images;
        public function __construct($images) {
            $this->images = $images;
        }
        public function display() {
            $template = file_get_contents(COMPONENTS."carrousel/carrousel_template.php");
            $span_str = "";
            foreach($this->images as $image) {
                $span_str.="<span></span>";
            }
            $imgs_str = "";
            foreach($this->images as $image) {
                $imgs_str.="<img src=\"$image\" alt=\"\">";
            }
            $template = str_replace("{images_span_el_str}", $span_str, $template);
            $template = str_replace("{images_el_str}", $imgs_str, $template);
            echo($template);
        }
    }
?>
