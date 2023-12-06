<script src="components/carrousel/carrousel.js" defer></script>

<?php
    class Carrousel{
        private $images;
        public function __construct($images) {
            $this->images = $images;
        }
        public function display() {
            $template = file_get_contents("carrousel_template.php");
            foreach($this->images as $image) {
                echo("<span></span>");
            }
            foreach($this->images as $image) {
                echo(`<img src="$image" alt="">`);
            }
            $template = str_replace("{images_span_el_str}", "Title Here", $template);
            $template = str_replace("{images_el_str}", "Username Here", $template);
            echo($template);
        }
    }
?>
