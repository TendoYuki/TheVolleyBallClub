<?php require_once("/srv/http/endpoint/app-config.php") ?>
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
            echo(
                '
                <ul class="navbar">
                    <li class="navbar-menu-opener">
                        <svg class="nav-menu-symbol open" width="420" height="420" viewBox="0 0 420 420" fill="none" xmlns="http://www.w3.org/2000/svg">
                            <defs>
                                <style>
                                    .nav-menu-symbol {
                                        --symbol-color: black;
                                    }
                                </style>
                            </defs>
                            <path stroke="var(--symbol-color)" d="M63.0022 210L357.002 210M63 333.5L357 333.5M63.0022 87L357.002 87" stroke-width="73" stroke-linecap="round"/>
                        </svg>
                        <svg class="arrow-left-symbol close" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 245.5 418">
                            <defs>
                                <style>
                                    .arrow-left-symbol {
                                        --symbol-color: black;
                                    }
                                    .cls-1{
                                        fill: none;
                                        stroke: var(--symbol-color);
                                        stroke-linecap: round;
                                        stroke-linejoin: round;
                                        stroke-width: 73px;
                                    }
                                </style>
                            </defs>
                            <polyline class="cls-1" points="209 381.5 36.5 209 209 36.5"/>
                        </svg>
                    </li>
                    <ul class="navbar-menu">
                        <li '.($this->active==NavbarEntry::accueil ? 'class="selected"' : '').'><a href="/">ACCUEIL</a></li>
                        <li '.($this->active==NavbarEntry::informations ? 'class="selected"' : '').'><a href="/informations">INFORMATIONS</a></li>
                        <li '.($this->active==NavbarEntry::planning ? 'class="selected"' : '').'><a href="/planning">PLANNING</a></li>
                        <li '.($this->active==NavbarEntry::contact ? 'class="selected"' : '').'><a href="/contact">CONTACT</a></li>
                        '.(
                            isset($_SESSION["userConnect"]) ? ('<li '.($this->active==NavbarEntry::dashboard ? 'class="selected"' : '').'><a href="/dashboard/user">TABLEAU DE BORD</a></li>') : (
                            isset($_SESSION["adminConnect"]) ? ('<li '.($this->active==NavbarEntry::dashboard ? 'class="selected"' : '').'><a href="/dashboard/admin">TABLEAU DE BORD</a></li>') : '')
                        ).'
                        '.
                        (
                            (isset($_SESSION["userConnect"]) || isset($_SESSION["adminConnect"])) ?
                            '<li><a href="/connection/sign-out">DECONNEXION</a></li>' :
                            '<li '.($this->active==NavbarEntry::connection ? 'class="selected"' : '').'><a href="/connection/sign-in">CONNEXION</a></li>'
                        )
                        .'
                    </ul>
                </ul>
                '
            );
        }
    }
?>
