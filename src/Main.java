import models.Evento;
import models.Registro;
import models.Taxi;
import models.Chofer;
import models.Administracion;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner keyboard = new Scanner(System.in);
        Administracion admin = new Administracion();
        Registro registro = new Registro();
        Chofer driver = null;
        boolean continuar = true;

        do {
            System.out.println("¿Qué es Admin/Chofer?");
            String opcion1 = keyboard.nextLine();
            int opcion;
            ArrayList<Evento> listaViajes = new ArrayList<>();

            if (opcion1.equalsIgnoreCase("Admin")) {
                do {
                    System.out.println("¿Qué desea hacer?");
                    System.out.println("1.- Agregar Chofer");
                    System.out.println("2.- Agregar Unidad");
                    System.out.println("3.- Asignar Chofer a Unidad");
                    System.out.println("4.- Ver Taxis Disponibles");
                    System.out.println("5.- Finalizar Día ");
                    System.out.println("6.- Salir");

                    opcion = keyboard.nextInt();
                    keyboard.nextLine();

                    switch (opcion) {
                        case 1:
                            System.out.println("Agregar Chofer");
                            do {
                                driver = new Chofer();
                                System.out.print("Nombre: ");
                                driver.setNombre(keyboard.nextLine());
                                System.out.print("Apellido: ");
                                driver.setApellido(keyboard.nextLine());
                                System.out.println("Ingrese su ID");
                                driver.setId(keyboard.nextInt());
                                keyboard.nextLine();
                                if (admin.addChofer(driver)) {
                                    System.out.println("Registro exitoso");
                                } else {
                                    System.out.println("Error en el registro");
                                }
                                System.out.println("¿Desea agregar otro chofer? (S/N)");
                            } while (keyboard.nextLine().equalsIgnoreCase("S"));
                            break;

                        case 2:
                            System.out.println("Agregar Unidad:");
                            do {
                                Taxi car = new Taxi();
                                System.out.print("Modelo: ");
                                car.setModelo(keyboard.nextLine());
                                System.out.print("Placa: ");
                                car.setPlaca(keyboard.nextLine());
                                System.out.print("Año: ");
                                car.setAño(keyboard.nextInt());
                                keyboard.nextLine();
                                if (admin.addTaxi(car)) {
                                    System.out.println("Registro exitoso");
                                    keyboard.nextLine();
                                } else {
                                    System.out.println("Error en el registro");
                                }

                                System.out.println("Opciones\n1. Agregar unidad \n2. Salir");
                            } while (keyboard.nextInt() == 1);
                            break;

                        case 3:
                            System.out.println("Asignar Chofer a Unidad");
                            System.out.println("Choferes disponibles:");
                            for (Chofer chofer : admin.getChoferes()) {
                                if (chofer.isDisponible()) {
                                    System.out.println("Nombre =" + chofer.getNombre() + " - Id =" + chofer.getId());
                                }
                            }

                            System.out.print("Elija un Chofer (ID): ");
                            int choferId = keyboard.nextInt();
                            keyboard.nextLine();

                            System.out.println("Unidades disponibles:");
                            for (Taxi taxi : admin.getTaxisDisponibles()) {
                                System.out.println("Modelo: " + taxi.getModelo() + " - Placa: " + taxi.getPlaca());
                            }
                            System.out.print("Elija una Unidad (Placa): ");
                            String taxiPlaca = keyboard.nextLine();

                            if (admin.asignarChoferUnidad(choferId, taxiPlaca)) {
                                System.out.println("Unidad asignada con éxito.");

                                Chofer choferAsignado = admin.buscaChofer(choferId);
                                if (choferAsignado != null) {
                                    choferAsignado.setDisponible(false);
                                }
                            } else {
                                System.out.println("Error al asignar la unidad.");
                            }
                            break;

                        case 4:
                            System.out.println("Taxis Disponibles:");
                            for (Taxi taxi : admin.getTaxisDisponibles()) {
                                System.out.println("Modelo: " + taxi.getModelo() + " - Placa: " + taxi.getPlaca() +
                                        " - Disponible: " + taxi.isDisponible());
                            }
                            break;

                        case 5:
                            System.out.println("Finalizar día");
                            String finalizar = keyboard.nextLine();
                            if (finalizar.equalsIgnoreCase("S")) {
                                admin.reiniciarEstado();
                                System.out.println("Día reiniciado. Todos los taxis y choferes están disponibles nuevamente.");
                            }
                            break;

                        case 6:
                            System.out.println("Volviendo al menú principal");
                            continuar = true;
                            ;
                            break;

                        default:
                            System.out.println("Opción no válida. Por favor, elija nuevamente.");
                            break;
                    }
            } while (opcion != 6 && continuar);

            } else if (opcion1.equalsIgnoreCase("Chofer")) {
                boolean choferValido = false;

                do {
                    System.out.println("Ingrese su ID:");

                    try {
                        int choferId = keyboard.nextInt();
                        keyboard.nextLine();
                        choferValido = true;

                        if (driver != null) {
                            System.out.println("¡Bienvenido, " + driver.getNombre() + " " + driver.getApellido() + "!");
                            do {
                                System.out.println("¿Qué desea hacer?");
                                System.out.println("1.- Agregar Servicio");
                                System.out.println("2.- Ver Servicios");
                                System.out.println("3.- Sacar Ganancia");
                                System.out.println("4.- Salir ");

                                opcion = keyboard.nextInt();
                                keyboard.nextLine();

                                switch (opcion) {
                                    case 1:
                                        Evento viaje = new Evento();
                                        System.out.println("¿Quiere agregar un servicio? (S/N)");
                                        String ser = keyboard.nextLine();

                                        if (ser.equalsIgnoreCase("S")) {
                                            System.out.println("Punto de partida: ");
                                            viaje.setPuntoPartida(keyboard.nextLine());
                                            System.out.println("Punto de destino: ");
                                            viaje.setDestino(keyboard.nextLine());
                                            System.out.println("Costo del viaje: ");
                                            viaje.setPago(keyboard.nextInt());

                                          LocalDateTime fechaHoraActual = LocalDateTime.now();
                                            viaje.setFechaHora(fechaHoraActual);
                                            listaViajes.add(viaje);
                                            registro.addEventoAsignado(viaje);
                                        } else if (ser.equalsIgnoreCase("N")) {
                                            break;
                                        }
                                        break;

                                    case 2:
                                        System.out.println("Cantidad de Servicios Realizados: " + registro.getEventos().size());

                                        if (!registro.getEventos().isEmpty()) {
                                            System.out.println("Servicios Realizados:");
                                            for (Evento servicio : registro.getEventos()) {
                                                System.out.println("Fecha y Hora: " + servicio.obtenerFechaHoraFormateada() +
                                                        ", Punto de partida: " + servicio.getPuntoPartida() +
                                                        ", Punto de destino: " + servicio.getDestino() +
                                                        ", Costo del viaje: " + servicio.getPago());
                                            }
                                        } else {
                                            System.out.println("No se han registrado servicios.");
                                        }
                                        break;

                                    case 3:
                                        double totalGastos = 0;

                                        for (Evento servicio : registro.getEventos()) {
                                            totalGastos += servicio.getPago();
                                        }

                                        System.out.println("Ganancias");
                                        System.out.println("Total de ingresos: " + totalGastos);
                                        System.out.println("Introduzca sus Gastos:");
                                        double gastos = keyboard.nextDouble();
                                        double ganancia = registro.calcularGanancia(totalGastos, gastos);
                                        System.out.println("Su ganancia es: " + ganancia);
                                        break;

                                    case 4:
                                        System.out.println("Volviendo al menú principal");
                                        continuar = false;
                                        break;

                                    default:
                                        System.out.println("Opción no válida. Por favor, elija nuevamente.");
                                        break;
                                }

                            } while (opcion != 4 && continuar);

                        } else {
                            System.out.println("Chofer no encontrado. Vuelva a intentarlo.");
                        }
                    } catch (Exception e) {
                        System.out.println("Error al leer la entrada. Vuelva a intentarlo.");
                        keyboard.nextLine();
                    }
                } while (!choferValido);

            }

        } while (continuar);

        System.out.println("Gracias por utilizar el programa. ¡Hasta luego!");
    }
}
