from mininet.net import Mininet

from mininet.node import Controller, OVSKernelSwitch

from mininet.cli import CLI

from mininet.log import setLogLevel, info

from mininet.link import TCLink



def createTopology():

    net = Mininet(controller=Controller, link=TCLink, switch=OVSKernelSwitch)



    info('* Adding controller\n')

    net.addController('c0')



    info('* Adding hosts and switches\n')

    # LAN 1

    h1 = net.addHost('h1', ip='10.0.1.1/24')

    h2 = net.addHost('h2', ip='10.0.1.2/24')

    s1 = net.addSwitch('s1')



    # LAN 2

    g1 = net.addHost('g1', ip='10.0.2.1/24')

    g2 = net.addHost('g2', ip='10.0.2.2/24')

    s2 = net.addSwitch('s2')



    # Router

    r1 = net.addHost('r1')



    info('* Creating links\n')

    # LAN 1 Links

    net.addLink(h1, s1)

    net.addLink(h2, s1)

    net.addLink(s1, r1, intfName2='r1-eth1', params2={'ip': '10.0.1.254/24'})



    # LAN 2 Links

    net.addLink(g1, s2)

    net.addLink(g2, s2)

    net.addLink(s2, r1, intfName2='r1-eth2', params2={'ip': '10.0.2.254/24'})



    info('* Starting network\n')

    net.start()



    # Configure router

    info('* Configuring router\n')

    r1.cmd('sysctl -w net.ipv4.ip_forward=1')

    r1.cmd('ifconfig r1-eth1 10.0.1.254/24')

    r1.cmd('ifconfig r1-eth2 10.0.2.254/24')



    # Configure routes

    info('* Configuring routes\n')

    h1.cmd('ip route add default via 10.0.1.254')

    h2.cmd('ip route add default via 10.0.1.254')

    g1.cmd('ip route add default via 10.0.2.254')

    g2.cmd('ip route add default via 10.0.2.254')



    info('* Running CLI\n')

    CLI(net)



    info('* Stopping network\n')

    net.stop()



if __name__ == '__main__':

    setLogLevel('info')

    createTopology()
