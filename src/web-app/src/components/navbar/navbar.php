<?php include_once("/srv/http/endpoint/config/config.php"); ?>
<?php include_once(TEMPLATES."template.php") ?>
<script src="/components/navbar/navbar.js" defer></script>

<?php
    enum NavbarEntry{
        case accueil;
        case informations;
        case planning;
        case contact;
        case connection;
        case dashboard;
        case none;

    }
    class Navbar {
        private $active;
        public function __construct($active) {
            $this->active = $active;
        }

        public function display() {
            $template = new Template(COMPONENTS."navbar/templates/navbar_template.php");

            $entries_str = '
                <li '.($this->active==NavbarEntry::accueil ? 'class="selected"' : '').'><a href="/">ACCUEIL</a></li>
                <li '.($this->active==NavbarEntry::informations ? 'class="selected"' : '').'><a href="/info">INFORMATIONS</a></li>
                <li '.($this->active==NavbarEntry::planning ? 'class="selected"' : '').'><a href="/planning">PLANNING</a></li>
                <li '.($this->active==NavbarEntry::contact ? 'class="selected"' : '').'><a href="/contact">CONTACT</a></li>
            ';

            if(isset($_SESSION["userConnect"]) || isset($_SESSION["adminConnect"])) {
                $entries_str = $entries_str.'
                    <li '.($this->active==NavbarEntry::dashboard ? 'class="selected"' : '').'><a href="/dashboard">TABLEAU DE BORD</a></li>';

                // Displays disconnect button
                $entries_str = $entries_str.'
                    <li>
                        <a href="/sign-out">DECONNEXION</a>
                    </li>';
            }
            // If not connected then display connect button
            else {
                $entries_str = $entries_str.'
                    <li '.($this->active==NavbarEntry::connection ? 'class="selected"' : '').'>
                        <a href="/sign-in">CONNEXION</a>
                    </li>';
            }

            $template->fill_placeholder("navbar_entries_str", $entries_str);

            $template->display();
        }
    }
?>
