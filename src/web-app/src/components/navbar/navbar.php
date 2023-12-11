<?php require_once("/srv/http/endpoint/app-config.php") ?>
<script src="/components/navbar/navbar.js" defer></script>

<?php
    enum NavbarEntry{
        case accueil;
        case informations;
        case planning;
        case contact;
        case connection;

    }
    class Navbar {
        private $active;
        public function __construct($active) {
            $this->active = $active;
        }

        public function display() {
            echo(
                '
                <ul class="navbar">
                    <ul class="navbar-menu">
                        <li '.($this->active==NavbarEntry::accueil ? 'class="selected"' : '').'><a href="/">ACCUEIL</a></li>
                        <li '.($this->active==NavbarEntry::informations ? 'class="selected"' : '').'><a href="/informations">INFORMATIONS</a></li>
                        <li '.($this->active==NavbarEntry::planning ? 'class="selected"' : '').'><a href="/planning">PLANNING</a></li>
                        <li '.($this->active==NavbarEntry::contact ? 'class="selected"' : '').'><a href="/contact">CONTACT</a></li>
                        <li '.($this->active==NavbarEntry::connection ? 'class="selected"' : '').'><a href="/connection/sign-in">CONNEXION</a></li>
                    </ul>
                    <li class="navbar-menu-opener">
                        <svg width="420" height="420" viewBox="0 0 420 420" fill="none" xmlns="http://www.w3.org/2000/svg">
                            <path d="M63.0022 210L357.002 210M63 333.5L357 333.5M63.0022 87L357.002 87" stroke-width="73" stroke-linecap="round"/>
                        </svg>
                    </li>
                </ul>
                '
            );
        }
    }
?>
